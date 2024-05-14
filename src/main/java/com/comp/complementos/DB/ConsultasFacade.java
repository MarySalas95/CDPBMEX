package com.comp.complementos.DB;

/**
 *
 * @author Mary Villanueva
 */
public class ConsultasFacade {

    private static ConsultasFacade consultaFacade;

    public static ConsultasFacade instance() {

        if (consultaFacade == null) {
            consultaFacade = new ConsultasFacade();
        }

        return consultaFacade;

    }

    private ConsultasFacade() {

    }

    public String getComplementos() { //Llamar complementos desde el filtro del Front solamente transaccion

        String sql;

        sql = "SELECT TRANSACCION, TIP FROM MP61BPCUSF.vw_mex_Complement_Send WHERE FECHA >= ? AND FECHA <= ? GROUP BY TRANSACCION, TIP ORDER BY TRANSACCION ";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getEdoCDP() {

        String sql;

        sql = "SELECT L.CESTD, H.CFOLIO, L.CTEXT "
                + "FROM MP61BPCUSF.COMPBMXD AS L "
                + "INNER JOIN MP61BPCUSF.COMPBMXH AS H ON H.CTRAN = L.CTRAN "
                + "WHERE L.CTRAN = ? "
                + "GROUP BY L.CESTD, H.CFOLIO, L.CTEXT "
                + "ORDER BY L.CTEXT DESC";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setInsertFolio() { //Insertar el Folio del Complemento en caso de no existir.

        String sql;

        sql = "INSERT INTO MP61BPCUSF.COMPBMXH (CTRAN) VALUES(?)";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getTransaccion() { //Consulta para determinar si los conceptos ya se encuentran registrados

        String sql;

        sql = "SELECT CTRAN FROM MP61BPCUSF.COMPBMXD WHERE CTRAN = ?";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setInsertConcepto() { //Insertar el registro de cada factura.

        String sql;

        sql = " INSERT INTO MP61BPCUSF.COMPBMXD "
                + " (CDOCU,CIMPO,CPAGO,CSALD ,CPARC,CSECU,CTRAN,CFECH,CBANK,CCUEN,CRFCB,CESTD,CTEXT,CFEPA) \n"
                + " VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setUpdateConcepto() { //Actualziar el registro de cada factura.

        String sql;

        sql = "UPDATE MP61BPCUSF.COMPBMXD SET CTEXT = ?, CBANK = ?, CCUEN = ?, CRFCB = ? WHERE CTRAN = ? AND CESTD = 'NO' AND CDOCU = ?";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setUpdateEdoTXT() {

        String sql;

        sql = "UPDATE MP61BPCUSF.COMPBMXD SET CESTD = ? WHERE CTRAN = ? AND CESTD = 'NO'";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getCabeceraCDP() {

        String sql;

        //MJSV 19022024 V1
        /*sql = "SELECT S.FECHA,\n"
                + "       S.CLIENTE,\n"
                + "       B.RCRAZON AS NOMBRE,\n"
                + "       X.CFOLIO AS FOLIO, \n"
                + "       X.CTRAN AS TRANSACCION\n"
                + "FROM " + libreriaUsuario + ".vw_mex_Complement_Send S\n"
                + "INNER JOIN " + libreriaObjetos + ".RCMB01 AS B ON B.RCCUST = CLIENTE\n"
                + "INNER JOIN " + libreriaUsuario + ".COMPBMXH X ON X.CTRAN = TRANSACCION\n"
                + "WHERE FECHA >= ? AND FECHA <= ? \n"
                + "GROUP BY S.FECHA, S.CLIENTE,  B.RCRAZON, X.CFOLIO, X.CTRAN ORDER BY FOLIO";*/
        //MJSV 19022024 V2
//        sql = "SELECT S.FECHA,\n"
//                + "       S.CLIENTE,\n"
//                + "       B.RCRAZON AS NOMBRE,\n"
//                + "       X.CFOLIO AS FOLIO, \n"
//                + "       X.CTRAN AS TRANSACCION,\n"
//                + "       C.CBANK AS BANCO,\n"
//                + "       C.CCUEN AS CUENTA,\n"
//                + "       C.CRFCB AS RFC,\n"
//                + "       C.CTEXT AS MENSAJE\n"
//                + "FROM " + libreriaUsuario + ".vw_mex_Complement_Send S\n"
//                + "INNER JOIN " + libreriaObjetos + ".RCMB01 AS B ON B.RCCUST = CLIENTE\n"
//                + "INNER JOIN " + libreriaUsuario + ".COMPBMXH X ON X.CTRAN = TRANSACCION\n"
//                + "INNER JOIN " + libreriaUsuario + ".COMPBMXD C ON C.CTRAN = TRANSACCION\n"
//                + "WHERE FECHA >= ? AND FECHA <= ? \n"
//                + "GROUP BY S.FECHA, S.CLIENTE,  B.RCRAZON, X.CFOLIO, X.CTRAN, C.CBANK, C.CCUEN, C.CRFCB, C.CTEXT\n"
//                + "ORDER BY FOLIO";
        //MJSV 21022024
        sql = "SELECT S.FECHA,\n"
                + "       S.CLIENTE,\n"
                + "       B.RCRAZON AS NOMBRE,\n"
                + "       X.CFOLIO AS FOLIO, \n"
                + "       X.CTRAN AS TRANSACCION,\n"
                + "       C.CBANK AS BANCO,\n"
                + "       C.CCUEN AS CUENTA,\n"
                + "       C.CRFCB AS RFC,\n"
                + "       (SELECT MAX(CTEXT) FROM MP61BPCUSF.COMPBMXD C  WHERE C.CTRAN = X.CTRAN AND C.CFEPA >= 20200824 AND C.CFEPA <= 20200824 AND (CTEXT LIKE '%01%' OR CTEXT LIKE '%03%' OR CTEXT LIKE '%02%') GROUP BY CTRAN, CTEXT ORDER BY CTEXT DESC LIMIT 1) AS MENSAJE, \n"
                + "       C.CESTD AS TXT "
                + "FROM MP61BPCUSF.vw_mex_Complement_Send S\n"
                + "INNER JOIN MP61BPCUSR.RCMB01 AS B ON B.RCCUST = CLIENTE\n"
                + "INNER JOIN MP61BPCUSF.COMPBMXH X ON X.CTRAN = TRANSACCION\n"
                + "INNER JOIN MP61BPCUSF.COMPBMXD C ON C.CTRAN = TRANSACCION\n"
                + "WHERE FECHA >= ? AND FECHA <= ? \n"
                + "GROUP BY S.FECHA, S.CLIENTE,  B.RCRAZON, X.CFOLIO, X.CTRAN, C.CBANK, C.CCUEN, C.CRFCB, C.CESTD \n"
                + "ORDER BY FOLIO";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getDetalleCDP() {

        String sql;

        sql = "SELECT C.CTRAN,\n"
                + "       CDOCU AS FACTURA, \n"
                + "       CPARC AS PARCIALIDAD, \n"
                + "       CIMPO AS SALDO_ANTERIOR, \n"
                + "       CPAGO AS MONTO_PAGO, \n"
                + "       CSALD AS NUEVO_SALDO, \n"
                + "       CASE WHEN S.SICURR = 'PSO' THEN 'MXN' ELSE S.SICURR END AS MONEDA,\n"
                + "       CBANK AS BANCO,\n"
                + "       CCUEN AS CUENTA,\n"
                + "       CRFCB AS RFC, \n"
                + "       CTEXT AS MENSAJE,\n"
                + "       S.IHDPFX AS PREFIJO\n"
                + "FROM MP61BPCUSF.COMPBMXD C\n"
                + "INNER JOIN MP61BPCSF.SIH S ON S.IHDOCN = C.CDOCU\n"
                + "INNER JOIN MP61BPCUSF.COMPBMXH X ON C.CTRAN = X.CTRAN\n"
                + "   WHERE CFEPA >= ? AND CFEPA <= ? AND C.CTRAN = ? \n"
                + "ORDER BY X.CFOLIO, CDOCU";

        //System.out.println("sql = " + sql);
        return sql;
    }

    //INICIA CONTROL DE UUID
    public String getUUID() {

        String sql;

        sql = "SELECT BLDOCU FROM MP61BPCUSF.BILB05 WHERE BLUUID = ?";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setInsertUUID() {

        String sql;

        sql = "INSERT INTO MP61BPCUSF.BILB05 (BLPREF, BLDOCU, BLUUID, BLSTAT) VALUES (?,?,?,?)";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setUpdateUUID() {

        String sql;

        sql = "UPDATE MP61BPCUSF.BILB05 SET BLPREF=?, BLDOCU=?, BLSTAT=? WHERE BLUUID=?";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setUpdateCtasBancarias() {

        String sql;

        sql = "UPDATE MP61BPCUSF.COMPBMXD SET CBANK = ?, CCUEN = ?, CRFCB = ?, CTEXT = ? WHERE CTRAN = ? AND CESTD = 'NO'";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String setUpdateMSG() {

        String sql;

        sql = "UPDATE MP61BPCUSF.COMPBMXD SET CTEXT = ? WHERE CDOCU = ? AND CTRAN = ?";

        //System.out.println("sql = " + sql);
        return sql;
    }
    
    public String getFTP() { //MJSV 10052024
        
        String sql;
        
        sql = "SELECT CCDESC AS IP, CCNOT1 AS USUARIO, CCNOT2 AS PASS FROM MP61BPCSF.ZCC WHERE CCTABL = 'SATPRMFE' AND CCCODE = 'ACCESOMEX'";
        
        return sql;
    }

    public String getFolio() { //NODO CFDI

        String sql;

        sql = "SELECT CFOLIO FROM MP61BPCUSF.COMPBMXH WHERE CTRAN = ?";

        //System.out.println("getFolio = " + sql);
        return sql;

    }

    public String getAcceso() { //NODO ACCESO

        String sql;

        sql = "SELECT CCCODE AS Valor, CCDESC AS Dato1, CCSDSC AS DAto2 FROM MP61BPCSF.ZCC WHERE CCTABL = 'SATPRMCP' AND CCCODE = 'USUARIO'";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getCFD40() { //NODO CFDI

        String sql;

        sql = "SELECT CCCODE AS ETIQUETA, CCDESC AS VALOR FROM MP61BPCSF.ZCC WHERE CCTABL = 'CDPCFD40'";

        //System.out.println("getCFD40 = " + sql);
        return sql;

    }

    public String getConsultaReceptor() { //NODO RECEPTOR

        String sql;

        //Consulta Inicial
        /*sql = " SELECT R.H3CUST AS NOCLIENTE, \n"
                + "        C.CMFTXC AS RFCR, \n"
                + "        B.RCRAZON AS NOMBRER, \n"
                + "        SUBSTRING(B.RCTEXT2,1,3) AS USOCFDI, \n"
                + "        B.RCMAIL1 AS EMAIL1, \n"
                + "        B.RCMAIL2 AS EMAIL2, \n"
                + "        B.RCMAIL3 AS EMAIL3, \n"
                + "        B.RCNIT AS NUMREGIDTRIB,\n"
                + "        C.CZIP AS DOMICILIOFISCALRECEPTOR, \n"
                + "        C.CMSTTP AS REGIMENFISCALRECEPTOR,  R.H3DREF AS TIP \n"
                + " FROM " + libreriaCartera + ".RDH3 AS R \n"
                + " INNER JOIN " + libreriaBPCS + ".RCM AS C ON R.H3CUST = C.CCUST \n"
                + " INNER JOIN " + libreriaObjetos + ".RCMB01 AS B ON C.CCUST = B.RCCUST AND B.RCCUST = R.H3CUST \r\n"
                + " WHERE R.H3TRNP = ? AND R.H3DREF IN ('SP','FF')";*/
        //Consulta MJSV 26092023
        sql = " SELECT R.H3CUST AS NOCLIENTE, \n"
                + "        C.CMFTXC AS RFCR, \n"
                + "        B.RCRAZON AS NOMBRER, \n"
                + "        SUBSTRING(B.RCTEXT2,1,3) AS USOCFDI, \n"
                + "        B.RCMAIL1 AS EMAIL1, \n"
                + "        B.RCMAIL2 AS EMAIL2, \n"
                + "        B.RCMAIL3 AS EMAIL3, \n"
                + "        B.RCNIT AS NUMREGIDTRIB,\n"
                + "        CASE WHEN RTRIM(C.CMFTXC) = 'XEXX010101000' THEN ( SELECT CCSDSC FROM MP61BPCSF.ZCC WHERE CCTABL = 'CDPRECEX' AND  CCDESC = C.CMFTXC )\n"
                + "             ELSE C.CZIP \n"
                + "        END AS DOMICILIOFISCALRECEPTOR, \n"
                + "        C.CMSTTP AS REGIMENFISCALRECEPTOR,  \n"
                + "        R.H3DREF AS TIP \n"
                + " FROM MP61CARF.RDH3 AS R \n"
                + " INNER JOIN MP61BPCSF.RCM AS C ON R.H3CUST = C.CCUST \n"
                + " INNER JOIN MP61BPCUSR.RCMB01 AS B ON C.CCUST = B.RCCUST AND B.RCCUST = R.H3CUST \n"
                + " WHERE R.H3TRNP = ? AND R.H3DREF IN ('SP','FF')";

        //System.out.println("getConsultaReceptor = " + sql);
        return sql;

    }

    //Se omite esta consulta ya que se integra en la consulta getConsultaReceptor()
    /*public String getDatosExpoRecep() { //NODO RECEPTOR
        
        String sql;

        sql = "SELECT CCSDSC FROM " + libreriaBPCS + ".ZCC WHERE CCTABL = 'CDPRECEX' AND  CCDESC = ?";

        System.out.println("getDatosExpoRecep = " + sql);
        return sql;

    }*/
    public String getFinanciera() { //NODO RECPETOR Y PAGOS

        String sql;

        sql = " SELECT B.RCRAZON AS RAZONSOCIAL,B.RCMAIL1 AS EMAIL1,B.RCMAIL2 AS EMAIL2,B.RCMAIL3 AS EMAIL3, R.CMFTXC AS RFC, \r\n"
                + "C.B7ACCT AS CUENTA, C.B7BANK AS BANKE, C.B7RFCB AS RFCB, B.RCNIT AS NIT, R.CZIP AS DOMICILIOFISCALRECEPTOR, R.CMSTTP AS REGIMENFISCALRECEPTOR \r\n"
                + "FROM MP61BPCSF.ZCC AS Z \r\n"
                + "INNER JOIN MP61BPCSF.RCM AS R ON Z.CCDESC = R.CCUST \r\n"
                + "INNER JOIN MP61BPCUSR.RCMB01 AS B ON Z.CCDESC = B.RCCUST \r\n"
                + "INNER JOIN MP61BPCUSF.BILB07 AS C ON Z.CCDESC = C.B7CUST \r\n" //Modif 25092023 MJSV
                //+ "INNER JOIN " + libreriaUsuario + ".BILB07 AS C ON Z.CCCODE = C.B7CUST AND C.B7RFCB = R.CTXID \r\n"
                + "WHERE CCCODE = ? AND Z.CCTABL = 'CDPFAFIN'";

        //System.out.println("getFinanciera = " + sql);
        return sql;

    }

    public String getConsultaConcepto() { //NODO CONCEPTO

        String sql;

        sql = "SELECT IPROD as CLAVE, IUMS AS UNIDAD, IDESC AS DESCRIPCION, ILIST AS VALOR, ILIST AS IMPORTE "
                + "FROM MP61BPCSF.IIM "
                + "WHERE IPROD = '84111506'";

        //System.out.println("getConsultaConcepto = " + sql);
        return sql;

    }

    public String getPagCam() { //NODO TOTALES

        String sql;

        sql = "SELECT\r\n"
                + "R.H3CUST AS CLIENTE,\r\n"
                + "R.H3CNVF AS TCAMBIO,sum(R.H3TAMT) AS MONTO, R.H3TRNP AS NUMOPE\r\n"
                + "FROM MP61CARF.RDH3 AS R WHERE R.H3TRNP = ? AND R.H3DREF IN ('SP','FF') group by R.H3CUST,R.H3CNVF,R.H3TRNP";

        //System.out.println("getPagCam = " + sql);
        return sql;

    }

    public String getImpuestos() { //NODO TOTALES

        String sql;

//        sql = "SELECT MAX(I.TXCUST) AS CLIENTE, I.TXINVN AS DOCUMENTO, MAX(I.TXTRCD) AS IMPUESTO, MAX(I.TXTXBA) AS BASETDR,MAX(R.H3TAMT) AS BASETP, MAX(I.TXTAXT) AS IMPORTETDR, SUM(S.SITOT) AS VFACTURA, MAX(Z.CCDESC) AS IMPUESTOTDR, MAX(Z.CCSDSC) AS TIPOFACTORTDR, \r\n"
//                + "MAX(Z.CCNOT1) AS TASAOCUOTATDR \r\n"
//                + "FROM " + libreriaBPCS + ".RTX AS I \r\n"
//                + "INNER JOIN " + libreriaCartera + ".RDH3 AS R ON R.H3DOCN = I.TXINVN AND R.H3CUST = I.TXCUST "
//                + "INNER JOIN " + libreriaBPCS + ".SIH AS S ON R.H3DPX=S.IHDPFX AND R.H3DYR=S.IHDYR AND R.H3DTP=S.IHDTYP AND  R.H3DOCN=S.IHDOCN AND I.TXINVN =  R.H3DOCN \r\n"
//                + "INNER JOIN " + libreriaBPCS + ".ZCC AS Z ON I.TXTRCD = Z.CCCODE AND z.CCTABL = 'SATIMPCO'\r\n"
//                + "WHERE R.H3DREF IN ('SP','FF') AND  I.TXTRCD LIKE ? AND R.H3TRNP = ? GROUP BY I.TXINVN";
        sql = "SELECT MAX(I.TXCUST) AS CLIENTE, "
                + "I.TXINVN AS DOCUMENTO, "
                + "MAX(I.TXTRCD) AS IMPUESTO, "
                + "MAX(I.TXTXBA) AS BASETDR,"
                + "SUM(DISTINCT(R.H3TAMT)) AS BASETP, "
                + "MAX(I.TXTAXT) AS IMPORTETDR, "
                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
                + "MAX(Z.CCDESC) AS IMPUESTOTDR, "
                + "MAX(Z.CCSDSC) AS TIPOFACTORTDR, "
                + "MAX(Z.CCNOT1) AS TASAOCUOTATDR "
                + "FROM MP61BPCSF.RTX AS I "
                + "INNER JOIN MP61CARF.RDH3 AS R ON R.H3DOCN = I.TXINVN AND R.H3CUST = I.TXCUST "
                + "INNER JOIN MP61BPCSF.SIH AS S ON R.H3DPX=S.IHDPFX AND R.H3DYR=S.IHDYR AND R.H3DTP=S.IHDTYP AND  R.H3DOCN=S.IHDOCN AND I.TXINVN =  R.H3DOCN "
                + "INNER JOIN MP61BPCSF.ZCC AS Z ON I.TXTRCD = Z.CCCODE AND z.CCTABL = 'SATIMPCO' "
                + "WHERE R.H3DREF IN ('SP','FF') AND  I.TXTRCD LIKE ? AND R.H3TRNP = ? GROUP BY I.TXINVN";

        return sql;

    }

    public String getConsultaPago() {

        String sql;

        //Consulta Inicial
//        sql = "SELECT \r\n"
//                + "R.H3DREF AS TIP,R.H3CUST AS CLIENTE,"
//                + "SUBSTRING(R.H3TDTE,1,4)||'-'||SUBSTRING(R.H3TDTE,5,2)||'-'|| SUBSTRING(R.H3TDTE,7,2) AS FECHA,"
//                + "CASE \r\n"
//                + "WHEN R.H3PAYC = SUBSTRING(Z.CCCODE,1,1) THEN SUBSTRING(Z.CCCODE,2,2) \r\n"
//                + "END AS CODIGOSAT, \r\n"
//                + "R.H3PAYC AS FORMAPAGO, \r\n"
//                + "CASE \r\n"
//                + "WHEN R.H3CURR = 'PSO' THEN 'MXN' \r\n"
//                + "WHEN  R.H3CURR <> 'PSO' THEN R.H3CURR \r\n"
//                + "END AS MONEDA,"
//                + "R.H3CNVF AS TCAMBIO,SUM(R.H3TAMT) AS MONTO, R.H3TRNP AS NUMOPE, B.B7RFCB AS RFC, B.B7BANK AS BANCO, B.B7ACCT AS ORDENANTE, COUNT(*) as FILAS, BANK.BKDATN AS RFCB, BANK.BKACCT AS CUENTAB \r\n"
//                + "FROM " + libreriaCartera + ".RDH3 AS R \r\n"
//                + "LEFT JOIN " + libreriaUsuario + ".BILB07 AS B ON B.B7CUST = R.H3CUST AND B.B7MNUS = '' \r\n"
//                + "INNER JOIN " + libreriaBPCS + ".ZCC AS Z ON R.H3PAYC = SUBSTRING(Z.CCCODE,1,1) AND Z.CCTABL = 'SATFPAGO' \r\n"
//                + "INNER JOIN " + libreriaCartera + ".RDI3 AS RI ON R.H3TSEQ = I3TSEQ \r\n"
//                + "INNER JOIN " + libreriaBPCS + ".ABK AS BANK ON R.H3CURR = BANK.BCURR AND BANK.BANK = RI.I3COLL \r\n"
//                + "WHERE R.H3TRNP = ? AND R.H3DREF IN ('SP','FF') GROUP BY R.H3TIME, R.H3PAYC , R.H3PAYC, R.H3CURR, R.H3CNVF,R.H3TRNP, B.B7RFCB ,  B.B7BANK,  B.B7ACCT, R.H3DREF, R.H3TDTE,Z.CCCODE,BANK.BKACCT,BANK.BKDATN,R.H3DREF,R.H3CUST";
        //Consulta MJSV 26092023
        sql = "SELECT \r\n"
                + "R.H3DREF AS TIP,R.H3CUST AS CLIENTE,"
                + "SUBSTRING(R.H3TDTE,1,4)||'-'||SUBSTRING(R.H3TDTE,5,2)||'-'|| SUBSTRING(R.H3TDTE,7,2) AS FECHA,"
                + "CASE \r\n"
                + "WHEN R.H3PAYC = SUBSTRING(Z.CCCODE,1,1) THEN SUBSTRING(Z.CCCODE,2,2) \r\n"
                + "END AS CODIGOSAT, \r\n"
                + "R.H3PAYC AS FORMAPAGO, \r\n"
                + "CASE \r\n"
                + "WHEN R.H3CURR = 'PSO' THEN 'MXN' \r\n"
                + "WHEN  R.H3CURR <> 'PSO' THEN R.H3CURR \r\n"
                + "END AS MONEDA,"
                + "R.H3CNVF AS TCAMBIO,SUM(R.H3TAMT) AS MONTO, R.H3TRNP AS NUMOPE, BANK.BKDATN AS RFCB, BANK.BKACCT AS CUENTAB \r\n"
                + "FROM MP61CARF.RDH3 AS R \r\n"
                + "INNER JOIN MP61BPCSF.ZCC AS Z ON R.H3PAYC = SUBSTRING(Z.CCCODE,1,1) AND Z.CCTABL = 'SATFPAGO' \r\n"
                + "INNER JOIN MP61CARF.RDI3 AS RI ON R.H3TSEQ = I3TSEQ \r\n"
                + "INNER JOIN MP61BPCSF.ABK AS BANK ON R.H3CURR = BANK.BCURR AND BANK.BANK = RI.I3COLL \r\n"
                + "WHERE R.H3TRNP = ? AND R.H3DREF IN ('SP','FF') \r\n"
                + "GROUP BY R.H3TIME, R.H3PAYC , R.H3PAYC, R.H3CURR, R.H3CNVF,R.H3TRNP, R.H3DREF, R.H3TDTE,Z.CCCODE,BANK.BKACCT,BANK.BKDATN,R.H3DREF,R.H3CUST";

        //System.out.println("getConsultaPago = " + sql);
        return sql;

    }

    public String getConsultaCtasBancariasAct() { //NODO PAGO Nueva consulta MJSV 26092023

        String sql;

        sql = "SELECT B.B7RFCB AS RFC, \n"
                + "   B.B7BANK AS BANCO, \n"
                + "   B.B7ACCT AS ORDENANTE,\n"
                + "   B.B7CUST,"
                + "   ROW_NUMBER() OVER ( ORDER BY B7RFCB) ROW_NUM\n"
                + "  FROM MP61BPCUSF.BILB07 AS B \n"
                + " WHERE B.B7CUST = ? AND B.B7ID = 'B7'";

        //System.out.println("sql = " + sql);
        return sql;

    }

    public String getConsultaDoctoRelaciondo() {

        String sql;

        //Consulta Inicial
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, S.IHDOCN AS FOLIOPAGO, MAX(Z.CCDESC) AS FOLIO,\r\n"
//				+ "CASE\r\n"
//				+ "WHEN max(S.SICURR) = 'PSO' THEN 'MXN'\r\n"
//				+ "WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR)\r\n"
//				+ "END AS MONEDADR, max(R.H3TAMT) AS IMPAGO,MAX(R.H3CUST) AS CLIENTE, SUM(S.SITOT) AS VFACTURA, sum(S.SITAX) as sumavfact,  MAX(R.H3TSEQ) AS SECUENCIA, max(CO.CPARC) AS PARCIALIDAD2,\r\n"
//				+ "MAX(R.H3TDTE) AS FECHAPAGO,(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST) AS NUMCUEN, MAX(CO.CCUEN) AS CINSER\n"
//		+ "FROM "+libreriaCartera+".RDH3 AS R\n"
//		+ "INNER JOIN "+libreriaBPCS+".SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN\n"
//		+ "LEFT JOIN "+libreriaUsuario+".COMPBMXD AS CO ON H3DOCN=CO.CDOCU\n"
//		+ "LEFT JOIN "+libreriaBPCS+".ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//		+ "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') group by  S.IHDOCN,R.H3CUST";
        //Consulta MJSV 02102023        
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, S.IHDOCN AS FOLIOPAGO, MAX(Z.CCDESC) AS FOLIO,\r\n"
//                + "CASE\r\n"
//                + "WHEN max(S.SICURR) = 'PSO' THEN 'MXN'\r\n"
//                + "WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR)\r\n"
//                + "END AS MONEDADR, max(R.H3TAMT) AS IMPAGO,MAX(R.H3CUST) AS CLIENTE, SUM(S.SITOT) AS VFACTURA, sum(S.SITAX) as sumavfact,  MAX(R.H3TSEQ) AS SECUENCIA, max(CO.CPARC) AS PARCIALIDAD2,\r\n"
//                + "MAX(R.H3TDTE) AS FECHAPAGO,(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST) AS NUMCUEN, MAX(CO.CCUEN) AS CINSER,\n"
//                + "( SELECT BLUUID AS UUID FROM " + libreriaUsuario + ".BILB05 WHERE BLDOCU = S.IHDOCN), \r\n "
//                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, MAX(S.SICNFC) AS TCFACT "
//                + "FROM " + libreriaCartera + ".RDH3 AS R\n"
//                + "INNER JOIN " + libreriaBPCS + ".SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN \n"
//                + "LEFT JOIN " + libreriaUsuario + ".COMPBMXD AS CO ON H3DOCN=CO.CDOCU\n"
//                + "LEFT JOIN " + libreriaBPCS + ".ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') AND CO.CTRAN = ? group by  S.IHDOCN,R.H3CUST";
        //Consulta MJSV 23102023
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, S.IHDOCN AS FOLIOPAGO, MAX(Z.CCDESC) AS FOLIO,\r\n"
//                + "CASE\r\n"
//                + "WHEN max(S.SICURR) = 'PSO' THEN 'MXN'\r\n"
//                + "WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR)\r\n"
//                + "END AS MONEDADR, max(R.H3TAMT) AS IMPAGO,MAX(R.H3CUST) AS CLIENTE, SUM(S.SITOT) AS VFACTURA, sum(S.SITAX) as sumavfact,  MAX(R.H3TSEQ) AS SECUENCIA, max(CO.CPARC) AS PARCIALIDAD2,\r\n"
//                + "MAX(R.H3TDTE) AS FECHAPAGO,(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST) AS NUMCUEN, MAX(CO.CCUEN) AS CINSER,\n"
//                + "( SELECT BLUUID AS UUID FROM " + libreriaUsuario + ".BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), \r\n "
//                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
//                //+ "MAX(S.SICNFC) AS TCFACT "
//                + "MAX(R.H3CNVF) AS TCAMBIO "
//                + "FROM " + libreriaCartera + ".RDH3 AS R\n"
//                + "INNER JOIN " + libreriaBPCS + ".SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN \n"
//                + "LEFT JOIN " + libreriaUsuario + ".COMPBMXD AS CO ON H3DOCN=CO.CDOCU\n"
//                + "LEFT JOIN " + libreriaBPCS + ".ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') AND CO.CTRAN = ? group by  S.IHDOCN,R.H3CUST";
        //Consulta MJSV 29122023 Esta consulta no aplica para las facturas consolidadas, es decir para facturas con varios pedidos, se modifica consulta para insertar los conceptos.
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, "
//                + "S.IHDOCN AS FOLIOPAGO, "
//                + "MAX(Z.CCDESC) AS FOLIO,\r\n"
//                + "CASE WHEN max(S.SICURR) = 'PSO' THEN 'MXN' WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR) END AS MONEDADR, "
//                + "SUM(DISTINCT(R.H3TAMT)) AS IMPAGO,"
//                + "MAX(R.H3CUST) AS CLIENTE, "
//                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
//                + "SUM(DISTINCT(S.SITAX)) AS SUMAVFACT, "
//                + "MAX(R.H3TSEQ) AS SECUENCIA, "
//                + "max(CO.CPARC) AS PARCIALIDAD2, "
//                + "MAX(R.H3TDTE) AS FECHAPAGO,"
//                + "(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST) AS NUMCUEN, "
//                + "MAX(CO.CCUEN) AS CINSER,\n"
//                + "( SELECT BLUUID AS UUID FROM " + libreriaUsuario + ".BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), "
//                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
//                //+ "MAX(S.SICNFC) AS TCFACT "
//                + "MAX(R.H3CNVF) AS TCAMBIO "
//                + "FROM " + libreriaCartera + ".RDH3 AS R "
//                + "INNER JOIN " + libreriaBPCS + ".SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN "
//                + "LEFT JOIN " + libreriaUsuario + ".COMPBMXD AS CO ON H3DOCN=CO.CDOCU "
//                + "LEFT JOIN " + libreriaBPCS + ".ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') AND CO.CTRAN = ? GROUP BY S.IHDOCN,R.H3CUST";
        //Consulta MJSV 14122023 Esta consulta no aplica para las facturas consolidadas, es decir para facturas con varios pedidos.
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, "
//                + "S.IHDOCN AS FOLIOPAGO, "
//                + "MAX(Z.CCDESC) AS FOLIO,\r\n"
//                + "CASE WHEN max(S.SICURR) = 'PSO' THEN 'MXN' WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR) END AS MONEDADR, "
//                + "SUM(DISTINCT(R.H3TAMT)) AS IMPAGO,"
//                + "MAX(R.H3CUST) AS CLIENTE, "
//                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
//                + "SUM(DISTINCT(S.SITAX)) AS SUMAVFACT, "
//                + "MAX(R.H3TSEQ) AS SECUENCIA, "
//                + "max(CO.CPARC) AS PARCIALIDAD2, "
//                + "MAX(R.H3TDTE) AS FECHAPAGO,"
//                + "(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') AS NUMCUEN, "
//                + "MAX(CO.CCUEN) AS CINSER,\n"
//                + "( SELECT BLUUID AS UUID FROM " + libreriaUsuario + ".BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), "
//                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
//                //+ "MAX(S.SICNFC) AS TCFACT "
//                + "MAX(R.H3CNVF) AS TCAMBIO "
//                + "FROM " + libreriaCartera + ".RDH3 AS R "
//                + "INNER JOIN " + libreriaBPCS + ".SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN "
//                + "LEFT JOIN " + libreriaUsuario + ".COMPBMXD AS CO ON H3DOCN=CO.CDOCU "
//                + "LEFT JOIN " + libreriaBPCS + ".ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') GROUP BY S.IHDOCN,R.H3CUST";
//Consulta MJSV 06032024 Esta consulta no aplica para las facturas consolidadas, es decir para facturas con varios pedidos.
//        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, "
//                + "S.IHDOCN AS FOLIOPAGO, "
//                + "MAX(Z.CCDESC) AS FOLIO,\r\n"
//                + "CASE WHEN max(S.SICURR) = 'PSO' THEN 'MXN' WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR) END AS MONEDADR, "
//                + "SUM(DISTINCT(R.H3TAMT)) AS IMPAGO,"
//                + "MAX(R.H3CUST) AS CLIENTE, "
//                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
//                + "SUM(DISTINCT(S.SITAX)) AS SUMAVFACT, "
//                + "MAX(R.H3TSEQ) AS SECUENCIA, "
//                + "max(CO.CPARC) AS PARCIALIDAD2, "
//                + "MAX(R.H3TDTE) AS FECHAPAGO,"
//                + "(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') AS NUMCUEN, "
//                + "CASE WHEN (SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') = 1 THEN "
//                + "(SELECT B.B7RFCB FROM MP61BPCUSF.BILB07 AS B WHERE B.B7CUST = R.H3CUST AND B.B7ID = 'B7') "
//                + "END AS RFCORDENANTE, "
//                + "CASE WHEN (SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') = 1 THEN "
//                + "(SELECT B.B7BANK FROM MP61BPCUSF.BILB07 AS B WHERE B.B7CUST = R.H3CUST AND B.B7ID = 'B7') "
//                + "END AS BANKORDENANTE, "
//                + "CASE WHEN (SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') = 1 THEN "
//                + "(SELECT B.B7ACCT FROM MP61BPCUSF.BILB07 AS B WHERE B.B7CUST = R.H3CUST AND B.B7ID = 'B7') "
//                + "END AS CTAORDENANTE, "
//                + "CASE WHEN (SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') = 1 THEN "
//                + "(SELECT B.B7ACCT FROM MP61BPCUSF.BILB07 AS B WHERE B.B7CUST = R.H3CUST AND B.B7ID = 'B7') "
//                + "END AS CTAORDENANTE, "
//                + "MAX(CO.CCUEN) AS CINSER,\n"
//                + "( SELECT BLUUID AS UUID FROM MP61BPCUSF.BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), "
//                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
//                //+ "MAX(S.SICNFC) AS TCFACT "
//                + "MAX(R.H3CNVF) AS TCAMBIO "
//                + "FROM MP61CARF.RDH3 AS R "
//                + "INNER JOIN MP61BPCSF.SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN "
//                + "LEFT JOIN MP61BPCUSF.COMPBMXD AS CO ON H3DOCN=CO.CDOCU "
//                + "LEFT JOIN MP61BPCSF.ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
//                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') GROUP BY S.IHDOCN,R.H3CUST";
//Consulta MJSV 13032024 Esta consulta no aplica para las facturas consolidadas, es decir para facturas con varios pedidos.
        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, "
                + "S.IHDOCN AS FOLIOPAGO, "
                + "MAX(Z.CCDESC) AS FOLIO,\r\n"
                + "CASE WHEN max(S.SICURR) = 'PSO' THEN 'MXN' WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR) END AS MONEDADR, "
                + "SUM(DISTINCT(R.H3TAMT)) AS IMPAGO,"
                + "MAX(R.H3CUST) AS CLIENTE, "
                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
                + "SUM(DISTINCT(S.SITAX)) AS SUMAVFACT, "
                + "MAX(R.H3TSEQ) AS SECUENCIA, "
                + "max(CO.CPARC) AS PARCIALIDAD2, "
                + "MAX(R.H3TDTE) AS FECHAPAGO,"
                + "(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') AS NUMCUEN, "
                + "MAX(CO.CCUEN) AS CINSER,\n"
                + "( SELECT BLUUID AS UUID FROM MP61BPCUSF.BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), "
                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
                + "CO.CBANK, " 
                + "CO.CCUEN, " 
                + "CO.CRFCB, "
                //+ "MAX(S.SICNFC) AS TCFACT "
                + "MAX(R.H3CNVF) AS TCAMBIO "
                + "FROM MP61CARF.RDH3 AS R "
                + "INNER JOIN MP61BPCSF.SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN "
                + "LEFT JOIN MP61BPCUSF.COMPBMXD AS CO ON H3DOCN=CO.CDOCU "
                + "LEFT JOIN MP61BPCSF.ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') "
                + "GROUP BY S.IHDOCN,R.H3CUST,CO.CBANK,CO.CCUEN,CO.CRFCB";

        //System.out.println("sql = " + sql);
        return sql;

    }

    public String getSaldoAnterior() {

        String sql;

        sql = "SELECT CSALD FROM MP61BPCUSF.COMPBMXD WHERE CDOCU = ? AND CPARC = ?";

        //System.out.println("sql = " + sql);
        return sql;

    }

    public String getImpuestosBB() {

        String sql;

        sql = "SELECT DISTINCT TXTRCD FROM MP61BPCSF.RTX AS I \r\n"
                + "INNER JOIN MP61CARF.RDH3 AS R ON R.H3DOCN = I.TXINVN AND R.H3CUST = I.TXCUST \r\n"
                + "WHERE TXTRCD = ? AND R.H3TRNP = ? AND R.H3DREF IN ('SP','FF') AND I.TXINVN = ?";

        //System.out.println("sql = " + sql);
        return sql;

    }

    public String getFacturaIva() {

        String sql;

        sql = "SELECT MAX(I.TXCUST) AS CLIENTE, "
                + "I.TXINVN AS DOCUMENTO, "
                + "MAX(I.TXTRCD) AS IMPUESTO, "
                + "SUM(DISTINCT(I.TXTXBA)) AS BASETDR, "
                + "SUM(DISTINCT(R.H3TAMT)) AS BASETP, "
                + "SUM(DISTINCT(I.TXTAXT)) AS IMPORTETDR, "
                + "SUM(DISTINCT(S.SITOT)) AS VFACTURA, "
                + "MAX(Z.CCDESC) AS IMPUESTOTDR, "
                + "MAX(Z.CCSDSC) AS TIPOFACTORTDR, "
                + "MAX(Z.CCNOT1) AS TASAOCUOTATDR "
                + "FROM MP61BPCSF.RTX AS I "
                + "INNER JOIN MP61CARF.RDH3 AS R ON R.H3DOCN = I.TXINVN AND R.H3CUST = I.TXCUST "
                + "INNER JOIN MP61BPCSF.SIH AS S ON R.H3DPX=S.IHDPFX AND R.H3DYR=S.IHDYR AND R.H3DTP=S.IHDTYP AND  R.H3DOCN=S.IHDOCN AND I.TXINVN =  R.H3DOCN "
                + "INNER JOIN MP61BPCSF.ZCC AS Z ON I.TXTRCD = Z.CCCODE AND Z.CCTABL = 'SATIMPCO' "
                + "WHERE R.H3DREF IN ('SP','FF') AND  I.TXTRCD LIKE ? AND R.H3TRNP = ? AND I.TXINVN = ?  GROUP BY I.TXINVN";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getImpuestosIVA00() {

        String sql;

        /*sql = "SELECT S.IHDOCN AS FOLIOPAGO, MAX(R.H3TAMT) AS IMPAGO, MAX(Z.CCNOT1) AS TASAOCUOTATDR, MAX(Z.CCDESC) AS IMPUESTOTDR, MAX(Z.CCSDSC) AS TIPOFACTORTDR\n " +
        "FROM "+ libreriaCartera +".RDH3 AS R\n " +
        "INNER JOIN "+libreriaBPCS+".SIH AS S ON R.H3DPX=S.IHDPFX AND R.H3DYR=S.IHDYR AND R.H3DTP=S.IHDTYP AND  R.H3DOCN=S.IHDOCN\n " +
        "INNER JOIN "+libreriaBPCS+".ZCC AS Z ON  Z.CCCODE = 'IVA00' AND Z.CCTABL = 'SATIMPCO'\n " +
        "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') AND S.IHDOCN = ?  GROUP BY  S.IHDOCN";*/
        sql = " SELECT SUM(ILREV) AS SUBTOTAL, SUM(ILTA01) AS IVA, ILTR01 AS IVA \n"
                + " FROM MP61BPCSF.SIL \n"
                + " WHERE ILINVN = ? AND (ILTR01 = 'IVA00' OR ILTR01 = '') \n"
                + " GROUP BY ILTR01";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getImpuestosIVA00T() {
        String sql;

        sql = " SELECT max(S.IHDPFX) AS SERIEPAGO, S.IHDOCN AS FOLIOPAGO, MAX(Z.CCDESC) AS FOLIO,\r\n"
                + "CASE\r\n"
                + "WHEN max(S.SICURR) = 'PSO' THEN 'MXN'\r\n"
                + "WHEN  max(S.SICURR) <> 'PSO' THEN max(S.SICURR)\r\n"
                + "END AS MONEDADR, max(R.H3TAMT) AS IMPAGO,MAX(R.H3CUST) AS CLIENTE, SUM(S.SITOT) AS VFACTURA, sum(S.SITAX) as sumavfact,  MAX(R.H3TSEQ) AS SECUENCIA, max(CO.CPARC) AS PARCIALIDAD2,\r\n"
                + "MAX(R.H3TDTE) AS FECHAPAGO,(SELECT COUNT(*) FROM MP61BPCUSF.BILB07 WHERE B7CUST = R.H3CUST AND B7ID = 'B7') AS NUMCUEN, MAX(CO.CCUEN) AS CINSER,\n"
                + "( SELECT BLUUID AS UUID FROM MP61BPCUSF.BILB05 WHERE BLDOCU = S.IHDOCN AND BLPREF =  MAX(S.IHDPFX)), \r\n "
                + "'02' AS OBJETOIMP, '01' AS EQUIVALENCIADR, "
                //+ "MAX(S.SICNFC) AS TCFACT "
                + "MAX(R.H3CNVF) AS TCAMBIO "
                + "FROM MP61CARF.RDH3 AS R\n"
                + "INNER JOIN MP61BPCSF.SIH AS S ON H3DPX=IHDPFX AND H3DYR=IHDYR AND H3DTP=IHDTYP AND  H3DOCN=IHDOCN \n"
                + "LEFT JOIN MP61BPCUSF.COMPBMXD AS CO ON H3DOCN=CO.CDOCU\n"
                + "LEFT JOIN MP61BPCSF.ZCC AS Z ON CCTABL = 'CDPCFD40' AND CCCODE = 'FOLIO'"
                + "WHERE H3TRNP = ? AND SUBSTRING(H3DREF,1,2) IN ('SP','FF') AND CO.CTRAN = ? AND S.IHDOCN = ? GROUP BY S.IHDOCN,R.H3CUST";

        //System.out.println("sql = " + sql);
        return sql;
    }

    public String getImpuestosDR() { //NODO Retenciones

        String sql;

        sql = "SELECT MAX(I.TXCUST) AS CLIENTE, I.TXINVN AS DOCUMENTO, MAX(I.TXTRCD) AS IMPUESTO, MAX(I.TXTXBA) AS BASETDR,MAX(R.H3TAMT) AS BASETP, MAX(I.TXTAXT) AS IMPORTETDR, SUM(S.SITOT) AS VFACTURA, MAX(Z.CCDESC) AS IMPUESTOTDR, MAX(Z.CCSDSC) AS TIPOFACTORTDR, \r\n"
                + "MAX(Z.CCNOT1) AS TASAOCUOTATDR \r\n"
                + "FROM MP61BPCSF.RTX AS I \r\n"
                + "INNER JOIN MP61CARF.RDH3 AS R ON R.H3DOCN = I.TXINVN AND R.H3CUST = I.TXCUST "
                + "INNER JOIN MP61BPCSF.SIH AS S ON R.H3DPX=S.IHDPFX AND R.H3DYR=S.IHDYR AND R.H3DTP=S.IHDTYP AND  R.H3DOCN=S.IHDOCN AND I.TXINVN =  R.H3DOCN \r\n"
                + "INNER JOIN MP61BPCSF.ZCC AS Z ON I.TXTRCD = Z.CCCODE AND z.CCTABL = 'SATIMPCO'\r\n"
                + "WHERE R.H3DREF IN ('SP','FF') AND  I.TXTRCD LIKE ? AND R.H3TRNP = ? AND I.TXINVN = ? GROUP BY I.TXINVN ";

        //System.out.println("getImpuestos = " + sql);
        return sql;

    }

}
