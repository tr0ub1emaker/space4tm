/*
 *============================================================================
 *
 * IBM Confidential
 *
 * (C) Copyright IBM Corp. 2015.
 *
 * The source code for this program is not published or otherwise divested of
 * its trade secrets, irrespective of what has been deposited with the U.S.
 * Copyright office.
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: Notification.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.bean;

/**
 * This class represents a bean object : Notification.
 * The member field must be the same as DB table which has the same name.
 * 
 * @version 1.00 07-29-2015
 */
public class Notification extends BeanBase {
	private String ID;
	private String ORDER_ID;
	private String LINE_ITEM_ID;
	private String FULLFILL_ORD_NUM;
	private String CUSTOMER_PO_NUM;
	private String CUSTOMER_REF_NUM;
	private String INAC_CODE;
	private String UI_ENTERPRISE;
	private String UI_COMPANY_NUMBER;
	private String CUST_NAME;
	private String UI_SOLDTO_CUST;
	private String SOLDTO_MPP_NUM;
	private String SOLDTO_CMR_NUM;
	private String ISO_COUNTRY_CODE;
	private String LEGACY_CTRY_CODE;
	private String O_ITEMSTATUS_CD;
	private String ITEMSTATUS_CD;
	private String O_PSSD;
	private String PSSD;
	private String O_CAD;
	private String CAD;
	private String NOTIFICATION_TYPE;
	private String UPDATE_TIMESTAMP;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getLINE_ITEM_ID() {
		return LINE_ITEM_ID;
	}
	public void setLINE_ITEM_ID(String lINE_ITEM_ID) {
		LINE_ITEM_ID = lINE_ITEM_ID;
	}
	public String getFULLFILL_ORD_NUM() {
		return FULLFILL_ORD_NUM;
	}
	public void setFULLFILL_ORD_NUM(String fULLFILL_ORD_NUM) {
		FULLFILL_ORD_NUM = fULLFILL_ORD_NUM;
	}
	public String getCUSTOMER_PO_NUM() {
		return CUSTOMER_PO_NUM;
	}
	public void setCUSTOMER_PO_NUM(String cUSTOMER_PO_NUM) {
		CUSTOMER_PO_NUM = cUSTOMER_PO_NUM;
	}
	public String getCUSTOMER_REF_NUM() {
		return CUSTOMER_REF_NUM;
	}
	public void setCUSTOMER_REF_NUM(String cUSTOMER_REF_NUM) {
		CUSTOMER_REF_NUM = cUSTOMER_REF_NUM;
	}
	public String getINAC_CODE() {
		return INAC_CODE;
	}
	public void setINAC_CODE(String iNAC_CODE) {
		INAC_CODE = iNAC_CODE;
	}
	public String getUI_ENTERPRISE() {
		return UI_ENTERPRISE;
	}
	public void setUI_ENTERPRISE(String uI_ENTERPRISE) {
		UI_ENTERPRISE = uI_ENTERPRISE;
	}
	public String getUI_COMPANY_NUMBER() {
		return UI_COMPANY_NUMBER;
	}
	public void setUI_COMPANY_NUMBER(String uI_COMPANY_NUMBER) {
		UI_COMPANY_NUMBER = uI_COMPANY_NUMBER;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	public String getUI_SOLDTO_CUST() {
		return UI_SOLDTO_CUST;
	}
	public void setUI_SOLDTO_CUST(String uI_SOLDTO_CUST) {
		UI_SOLDTO_CUST = uI_SOLDTO_CUST;
	}
	public String getSOLDTO_MPP_NUM() {
		return SOLDTO_MPP_NUM;
	}
	public void setSOLDTO_MPP_NUM(String sOLDTO_MPP_NUM) {
		SOLDTO_MPP_NUM = sOLDTO_MPP_NUM;
	}
	public String getSOLDTO_CMR_NUM() {
		return SOLDTO_CMR_NUM;
	}
	public void setSOLDTO_CMR_NUM(String sOLDTO_CMR_NUM) {
		SOLDTO_CMR_NUM = sOLDTO_CMR_NUM;
	}
	public String getISO_COUNTRY_CODE() {
		return ISO_COUNTRY_CODE;
	}
	public void setISO_COUNTRY_CODE(String iSO_COUNTRY_CODE) {
		ISO_COUNTRY_CODE = iSO_COUNTRY_CODE;
	}
	public String getLEGACY_CTRY_CODE() {
		return LEGACY_CTRY_CODE;
	}
	public void setLEGACY_CTRY_CODE(String lEGACY_CTRY_CODE) {
		LEGACY_CTRY_CODE = lEGACY_CTRY_CODE;
	}
	public String getO_ITEMSTATUS_CD() {
		return O_ITEMSTATUS_CD;
	}
	public void setO_ITEMSTATUS_CD(String o_ITEMSTATUS_CD) {
		O_ITEMSTATUS_CD = o_ITEMSTATUS_CD;
	}
	public String getITEMSTATUS_CD() {
		return ITEMSTATUS_CD;
	}
	public void setITEMSTATUS_CD(String iTEMSTATUS_CD) {
		ITEMSTATUS_CD = iTEMSTATUS_CD;
	}
	public String getO_PSSD() {
		return O_PSSD;
	}
	public void setO_PSSD(String o_PSSD) {
		O_PSSD = o_PSSD;
	}
	public String getPSSD() {
		return PSSD;
	}
	public void setPSSD(String pSSD) {
		PSSD = pSSD;
	}
	public String getO_CAD() {
		return O_CAD;
	}
	public void setO_CAD(String o_CAD) {
		O_CAD = o_CAD;
	}
	public String getCAD() {
		return CAD;
	}
	public void setCAD(String cAD) {
		CAD = cAD;
	}
	public String getNOTIFICATION_TYPE() {
		return NOTIFICATION_TYPE;
	}
	public void setNOTIFICATION_TYPE(String nOTIFICATION_TYPE) {
		NOTIFICATION_TYPE = nOTIFICATION_TYPE;
	}
	public String getUPDATE_TIMESTAMP() {
		return UPDATE_TIMESTAMP;
	}
	public void setUPDATE_TIMESTAMP(String uPDATE_TIMESTAMP) {
		UPDATE_TIMESTAMP = uPDATE_TIMESTAMP;
	}

}
