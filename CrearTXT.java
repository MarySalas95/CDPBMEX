package com.complementos;

import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import com.comp.complementos.DTO.PagoCliente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Mary Villanueva
 * 
 * Clase encargada de crear el archivo TXT y colocarlo en la ruta del FTP para su timbrado.
 * 
 */
public class CrearTXT {

    public void CreateTXT(PagoCliente pago) throws SQLException {
        
        Connection c = getInstance().getConnection();

        ComplementoPagoBuilder builder = new ComplementoPago20Builder(pago);
        ComplementoPago complemento = builder.buildComplemento();
        complemento.fill();

        try {
            PreparedStatement ps = c.prepareStatement(instance().getEdoCDP());
            ps.setInt(1, pago.getTransactionProcess());
            ResultSet rsEdo = ps.executeQuery();

            if (rsEdo.next()) {
                if (rsEdo.getString("CESTD").trim().equals("SI")) {
                    //Documento ya timbrado
                } else {
                    if (rsEdo.getString("CTEXT").trim().equals("")) {
                        try {

                            String contenido = complemento.createTXT();

                            String nomArchivo = "S" + rsEdo.getString("CFOLIO") + ".txt";
                            File archivo = new File(nomArchivo);
                            if (!archivo.exists()) {
                                archivo.createNewFile();
                            }
                            FileWriter fw = new FileWriter(archivo);
                            try (BufferedWriter bw = new BufferedWriter(fw)) {
                                bw.write(contenido);
                            }

                            //Actualizar estado del TXT
                            ps = c.prepareStatement(instance().setUpdateEdoTXT());
                            ps.setString(1, "SI");
                            ps.setInt(2, pago.getTransactionProcess());
                            ps.executeUpdate();
                            
                            //Obtener acceso al FTP
                            ps = c.prepareStatement(instance().getFTP());
                            ResultSet rsftp = ps.executeQuery();
                            
                            if(rsftp.next()){
                                ftp(archivo, nomArchivo, rsftp.getString("IP").trim(), rsftp.getString("USUARIO").trim(), rsftp.getString("PASS").trim());
                                archivo.delete();
                            }

                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            ps.close();
            getInstance().closeConnection(c);

        } catch (SQLException e) {
            out.println("Error en CrearTXT");
            try {
                c.rollback();
                out.println("Error en Consultas CrearTXT: " + e.getMessage());
            } catch (SQLException ex) {
                getLogger(ConsultarCDP.class.getName()).log(SEVERE, null, ex);
            }
        }

    }

    private void ftp(File archivo, String nomArchivo, String servidor, String user, String pass) {

        FTPClient ftpClient = new FTPClient();

        try {

            out.println("Creando TXT " + nomArchivo);
            ftpClient.connect(servidor, 21);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(BINARY_FILE_TYPE);
            File firstLocalFile = archivo;
            String firstRemoteFile = nomArchivo;
            try (InputStream inputStream = new FileInputStream(firstLocalFile)) {
                ftpClient.storeFile(firstRemoteFile, inputStream);
            }

        } catch (IOException ex) {
            out.println("Error en FTP = " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
