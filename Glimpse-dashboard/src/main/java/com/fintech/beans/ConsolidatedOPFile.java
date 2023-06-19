package com.fintech.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class ConsolidatedOPFile {
	
	@Id
	private Integer output_file_dtl_id;
	 
	private String process_guid;
	 
	private Integer clubbed_trades_count;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate trade_date;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDateTime trade_time;
	 
	private Integer dealers_in_competition;
	 
	private String side;
	 
	private String isin;
	 
	private String ticker;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate maturity;
	 
	private Double coupon_perc;
	 
	private Double size_in_MM_actual;
	 
	private String size_in_MM;
	 
	private String currency;
	 
	private Double price_actual;
	 
	private Double price;
	 
	private Double mid_price_actual;
	 
	private Double mid_price;
	 
	private Double yield_perc;
	 
	private Double spread;
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private LocalDate settlement_date;
	 
	private String on_venue;
	 
	private String venue_actual;
	 
	private String venue_mic;
	 
	private String venue_identifier;
	 
	private String process_trade;
	 
	private String auto_execution;
	 
	private String portfolio_trade;
	 
	private String buy_side;
	 
	private String buy_side_lei;
	 
	private String client_unique_key;
	 
	private String counter_party;
	 
	private String counter_party_lei;
	 
	private String counterparty_identifier;
	 
	private String secmst_entity_name;
	 
	private String secmst_ticker;
	 
	private String secmst_sector;
	 
	private String secmst_country;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate secmst_issue_date;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate secmst_maturity_date;
	 
	private String secmst_issue_currency;
	 
	private BigDecimal secmst_issuance_coupon;
	 
	private String secmst_coupon_type;
	 
	private String  secmst_bond_type;
	 
	private BigDecimal secmst_amount_issued;
	 
	private String secmst_seniority;
	 
	private String secmst_credit_grade;
	 
	private String internal_cross;
	 
	private Float fill_ratio;
	 
	private String clubbed_trade_ids;
	 
	private Short is_active;
	 
	private String file_name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate file_generation_date;
	 
	private Integer entry_by;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDate entry_dt_time;
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private LocalDate updated_date;
	 
	private Double size_in_MM_capped_num;
	public ConsolidatedOPFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsolidatedOPFile(Integer output_file_dtl_id, String process_guid, Integer clubbed_trades_count,
			LocalDate trade_date, LocalDateTime trade_time, Integer dealers_in_competition, String side, String isin,
			String ticker, LocalDate maturity, Double coupon_perc, Double size_in_MM_actual, String size_in_MM,
			String currency, Double price_actual, Double price, Double mid_price_actual, Double mid_price,
			Double yield_perc, Double spread, LocalDate settlement_date, String on_venue, String venue_actual,
			String venue_mic, String venue_identifier, String process_trade, String auto_execution,
			String portfolio_trade, String buy_side, String buy_side_lei, String client_unique_key,
			String counter_party, String counter_party_lei, String counterparty_identifier, String secmst_entity_name,
			String secmst_ticker, String secmst_sector, String secmst_country, LocalDate secmst_issue_date,
			LocalDate secmst_maturity_date, String secmst_issue_currency, BigDecimal secmst_issuance_coupon,
			String secmst_coupon_type, String secmst_bond_type, BigDecimal secmst_amount_issued,
			String secmst_seniority, String secmst_credit_grade, String internal_cross, Float fill_ratio,
			String clubbed_trade_ids, Short is_active, String file_name, LocalDate file_generation_date,
			Integer entry_by, LocalDate entry_dt_time, LocalDate updated_date, Double size_in_MM_capped_num) {
		super();
		this.output_file_dtl_id = output_file_dtl_id;
		this.process_guid = process_guid;
		this.clubbed_trades_count = clubbed_trades_count;
		this.trade_date = trade_date;
		this.trade_time = trade_time;
		this.dealers_in_competition = dealers_in_competition;
		this.side = side;
		this.isin = isin;
		this.ticker = ticker;
		this.maturity = maturity;
		this.coupon_perc = coupon_perc;
		this.size_in_MM_actual = size_in_MM_actual;
		this.size_in_MM = size_in_MM;
		this.currency = currency;
		this.price_actual = price_actual;
		this.price = price;
		this.mid_price_actual = mid_price_actual;
		this.mid_price = mid_price;
		this.yield_perc = yield_perc;
		this.spread = spread;
		this.settlement_date = settlement_date;
		this.on_venue = on_venue;
		this.venue_actual = venue_actual;
		this.venue_mic = venue_mic;
		this.venue_identifier = venue_identifier;
		this.process_trade = process_trade;
		this.auto_execution = auto_execution;
		this.portfolio_trade = portfolio_trade;
		this.buy_side = buy_side;
		this.buy_side_lei = buy_side_lei;
		this.client_unique_key = client_unique_key;
		this.counter_party = counter_party;
		this.counter_party_lei = counter_party_lei;
		this.counterparty_identifier = counterparty_identifier;
		this.secmst_entity_name = secmst_entity_name;
		this.secmst_ticker = secmst_ticker;
		this.secmst_sector = secmst_sector;
		this.secmst_country = secmst_country;
		this.secmst_issue_date = secmst_issue_date;
		this.secmst_maturity_date = secmst_maturity_date;
		this.secmst_issue_currency = secmst_issue_currency;
		this.secmst_issuance_coupon = secmst_issuance_coupon;
		this.secmst_coupon_type = secmst_coupon_type;
		this.secmst_bond_type = secmst_bond_type;
		this.secmst_amount_issued = secmst_amount_issued;
		this.secmst_seniority = secmst_seniority;
		this.secmst_credit_grade = secmst_credit_grade;
		this.internal_cross = internal_cross;
		this.fill_ratio = fill_ratio;
		this.clubbed_trade_ids = clubbed_trade_ids;
		this.is_active = is_active;
		this.file_name = file_name;
		this.file_generation_date = file_generation_date;
		this.entry_by = entry_by;
		this.entry_dt_time = entry_dt_time;
		this.updated_date = updated_date;
		this.size_in_MM_capped_num = size_in_MM_capped_num;
	}
	public Integer getOutput_file_dtl_id() {
		return output_file_dtl_id;
	}
	public void setOutput_file_dtl_id(Integer output_file_dtl_id) {
		this.output_file_dtl_id = output_file_dtl_id;
	}
	public String getProcess_guid() {
		return process_guid;
	}
	public void setProcess_guid(String process_guid) {
		this.process_guid = process_guid;
	}
	public Integer getClubbed_trades_count() {
		return clubbed_trades_count;
	}
	public void setClubbed_trades_count(Integer clubbed_trades_count) {
		this.clubbed_trades_count = clubbed_trades_count;
	}
	public LocalDate getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(LocalDate trade_date) {
		this.trade_date = trade_date;
	}
	public LocalDateTime getTrade_time() {
		return trade_time;
	}
	public void setTrade_time(LocalDateTime trade_time) {
		this.trade_time = trade_time;
	}
	public Integer getDealers_in_competition() {
		return dealers_in_competition;
	}
	public void setDealers_in_competition(Integer dealers_in_competition) {
		this.dealers_in_competition = dealers_in_competition;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public LocalDate getMaturity() {
		return maturity;
	}
	public void setMaturity(LocalDate maturity) {
		this.maturity = maturity;
	}
	public Double getCoupon_perc() {
		return coupon_perc;
	}
	public void setCoupon_perc(Double coupon_perc) {
		this.coupon_perc = coupon_perc;
	}
	public Double getSize_in_MM_actual() {
		return size_in_MM_actual;
	}
	public void setSize_in_MM_actual(Double size_in_MM_actual) {
		this.size_in_MM_actual = size_in_MM_actual;
	}
	public String getSize_in_MM() {
		return size_in_MM;
	}
	public void setSize_in_MM(String size_in_MM) {
		this.size_in_MM = size_in_MM;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getPrice_actual() {
		return price_actual;
	}
	public void setPrice_actual(Double price_actual) {
		this.price_actual = price_actual;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMid_price_actual() {
		return mid_price_actual;
	}
	public void setMid_price_actual(Double mid_price_actual) {
		this.mid_price_actual = mid_price_actual;
	}
	public Double getMid_price() {
		return mid_price;
	}
	public void setMid_price(Double mid_price) {
		this.mid_price = mid_price;
	}
	public Double getYield_perc() {
		return yield_perc;
	}
	public void setYield_perc(Double yield_perc) {
		this.yield_perc = yield_perc;
	}
	public Double getSpread() {
		return spread;
	}
	public void setSpread(Double spread) {
		this.spread = spread;
	}
	public LocalDate getSettlement_date() {
		return settlement_date;
	}
	public void setSettlement_date(LocalDate settlement_date) {
		this.settlement_date = settlement_date;
	}
	public String getOn_venue() {
		return on_venue;
	}
	public void setOn_venue(String on_venue) {
		this.on_venue = on_venue;
	}
	public String getVenue_actual() {
		return venue_actual;
	}
	public void setVenue_actual(String venue_actual) {
		this.venue_actual = venue_actual;
	}
	public String getVenue_mic() {
		return venue_mic;
	}
	public void setVenue_mic(String venue_mic) {
		this.venue_mic = venue_mic;
	}
	public String getVenue_identifier() {
		return venue_identifier;
	}
	public void setVenue_identifier(String venue_identifier) {
		this.venue_identifier = venue_identifier;
	}
	public String getProcess_trade() {
		return process_trade;
	}
	public void setProcess_trade(String process_trade) {
		this.process_trade = process_trade;
	}
	public String getAuto_execution() {
		return auto_execution;
	}
	public void setAuto_execution(String auto_execution) {
		this.auto_execution = auto_execution;
	}
	public String getPortfolio_trade() {
		return portfolio_trade;
	}
	public void setPortfolio_trade(String portfolio_trade) {
		this.portfolio_trade = portfolio_trade;
	}
	public String getBuy_side() {
		return buy_side;
	}
	public void setBuy_side(String buy_side) {
		this.buy_side = buy_side;
	}
	public String getBuy_side_lei() {
		return buy_side_lei;
	}
	public void setBuy_side_lei(String buy_side_lei) {
		this.buy_side_lei = buy_side_lei;
	}
	public String getClient_unique_key() {
		return client_unique_key;
	}
	public void setClient_unique_key(String client_unique_key) {
		this.client_unique_key = client_unique_key;
	}
	public String getCounter_party() {
		return counter_party;
	}
	public void setCounter_party(String counter_party) {
		this.counter_party = counter_party;
	}
	public String getCounter_party_lei() {
		return counter_party_lei;
	}
	public void setCounter_party_lei(String counter_party_lei) {
		this.counter_party_lei = counter_party_lei;
	}
	public String getCounterparty_identifier() {
		return counterparty_identifier;
	}
	public void setCounterparty_identifier(String counterparty_identifier) {
		this.counterparty_identifier = counterparty_identifier;
	}
	public String getSecmst_entity_name() {
		return secmst_entity_name;
	}
	public void setSecmst_entity_name(String secmst_entity_name) {
		this.secmst_entity_name = secmst_entity_name;
	}
	public String getSecmst_ticker() {
		return secmst_ticker;
	}
	public void setSecmst_ticker(String secmst_ticker) {
		this.secmst_ticker = secmst_ticker;
	}
	public String getSecmst_sector() {
		return secmst_sector;
	}
	public void setSecmst_sector(String secmst_sector) {
		this.secmst_sector = secmst_sector;
	}
	public String getSecmst_country() {
		return secmst_country;
	}
	public void setSecmst_country(String secmst_country) {
		this.secmst_country = secmst_country;
	}
	public LocalDate getSecmst_issue_date() {
		return secmst_issue_date;
	}
	public void setSecmst_issue_date(LocalDate secmst_issue_date) {
		this.secmst_issue_date = secmst_issue_date;
	}
	public LocalDate getSecmst_maturity_date() {
		return secmst_maturity_date;
	}
	public void setSecmst_maturity_date(LocalDate secmst_maturity_date) {
		this.secmst_maturity_date = secmst_maturity_date;
	}
	public String getSecmst_issue_currency() {
		return secmst_issue_currency;
	}
	public void setSecmst_issue_currency(String secmst_issue_currency) {
		this.secmst_issue_currency = secmst_issue_currency;
	}
	public BigDecimal getSecmst_issuance_coupon() {
		return secmst_issuance_coupon;
	}
	public void setSecmst_issuance_coupon(BigDecimal secmst_issuance_coupon) {
		this.secmst_issuance_coupon = secmst_issuance_coupon;
	}
	public String getSecmst_coupon_type() {
		return secmst_coupon_type;
	}
	public void setSecmst_coupon_type(String secmst_coupon_type) {
		this.secmst_coupon_type = secmst_coupon_type;
	}
	public String getSecmst_bond_type() {
		return secmst_bond_type;
	}
	public void setSecmst_bond_type(String secmst_bond_type) {
		this.secmst_bond_type = secmst_bond_type;
	}
	public BigDecimal getSecmst_amount_issued() {
		return secmst_amount_issued;
	}
	public void setSecmst_amount_issued(BigDecimal secmst_amount_issued) {
		this.secmst_amount_issued = secmst_amount_issued;
	}
	public String getSecmst_seniority() {
		return secmst_seniority;
	}
	public void setSecmst_seniority(String secmst_seniority) {
		this.secmst_seniority = secmst_seniority;
	}
	public String getSecmst_credit_grade() {
		return secmst_credit_grade;
	}
	public void setSecmst_credit_grade(String secmst_credit_grade) {
		this.secmst_credit_grade = secmst_credit_grade;
	}
	public String getInternal_cross() {
		return internal_cross;
	}
	public void setInternal_cross(String internal_cross) {
		this.internal_cross = internal_cross;
	}
	public Float getFill_ratio() {
		return fill_ratio;
	}
	public void setFill_ratio(Float fill_ratio) {
		this.fill_ratio = fill_ratio;
	}
	public String getClubbed_trade_ids() {
		return clubbed_trade_ids;
	}
	public void setClubbed_trade_ids(String clubbed_trade_ids) {
		this.clubbed_trade_ids = clubbed_trade_ids;
	}
	public Short getIs_active() {
		return is_active;
	}
	public void setIs_active(Short is_active) {
		this.is_active = is_active;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public LocalDate getFile_generation_date() {
		return file_generation_date;
	}
	public void setFile_generation_date(LocalDate file_generation_date) {
		this.file_generation_date = file_generation_date;
	}
	public Integer getEntry_by() {
		return entry_by;
	}
	public void setEntry_by(Integer entry_by) {
		this.entry_by = entry_by;
	}
	public LocalDate getEntry_dt_time() {
		return entry_dt_time;
	}
	public void setEntry_dt_time(LocalDate entry_dt_time) {
		this.entry_dt_time = entry_dt_time;
	}
	public LocalDate getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(LocalDate updated_date) {
		this.updated_date = updated_date;
	}
	public Double getSize_in_MM_capped_num() {
		return size_in_MM_capped_num;
	}
	public void setSize_in_MM_capped_num(Double size_in_MM_capped_num) {
		this.size_in_MM_capped_num = size_in_MM_capped_num;
	}
	
	
}
