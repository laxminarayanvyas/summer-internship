package com.fintech.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class OutputFile {
	
	//private int output_file_dtl_id;
	@Column(name="DealersInCompetition")
	//@Nullable
	private Long dealers_in_competition; //dealers_in_competition
	private String side;
	@Id
	private String isin;
	private String ticker;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate maturity;
	private Float spread;
	private String currency; 
	private Float price;
	private String venue;
	@Column(name="CouponPerc")
	private  Float coupon_perc;
	@Column(name="SizeMM")
	private String size_in_MM_actual;
	@Column(name="TradeDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate trade_date;
	@Column(name="TradeTime")
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDateTime trade_time;
	@Column(name="MidPrice")
	private Float mid_price;
	@Column(name="YieldPerc")
	private Float yield_perc;
	@Column(name="SettlementDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate settlement_date;
	@Column(name="Onvenue")
	private String on_venue;
	@Column(name="ProcessTrade")
	private String process_trade;
	@Column(name="PortfolioTrade")
	private String portfolio_trade;
	@Column(name="AutoExecution")
	private String auto_execution;
	
	public OutputFile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OutputFile(LocalDate trade_date, LocalDateTime trade_time, Long dealers_in_competition, String side, String isin,
			String ticker, LocalDate maturity, Float coupon_perc, String size_in_MM_actual, String currency, Float price,
			Float mid_price, Float yield_perc, Float spread, LocalDate settlement_date, String on_venue, String venue,
			String process_trade, String auto_execution, String portfolio_trade) {
		super();
		this.side = side;
		this.isin = isin;
		this.ticker = ticker;
		this.maturity = maturity;
		this.currency = currency;
		this.venue = venue;
		this.price = price;
		this.spread = spread;
		this.mid_price = mid_price;
		this.yield_perc = yield_perc;
		this.settlement_date = settlement_date;
		this.coupon_perc = coupon_perc;
		this.dealers_in_competition = dealers_in_competition;
		this.size_in_MM_actual = size_in_MM_actual;
		this.on_venue = on_venue;
		this.process_trade = process_trade;
		this.auto_execution = auto_execution;
		this.portfolio_trade = portfolio_trade;
		this.trade_date = trade_date;
		this.trade_time = trade_time;
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


	
	  public Long getDealers_in_competition() { return dealers_in_competition; }
	  
	  
	  public void setDealers_in_competition(Long dealers_in_competition) {
	  this.dealers_in_competition = dealers_in_competition; }
	 


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


	
	  public Float getCoupon_perc() { return coupon_perc; }
	  
	  
	  public void setCoupon_perc(Float coupon_perc) { this.coupon_perc =
	  coupon_perc; }
	 


	public String getSize_in_MM_actual() {
		return size_in_MM_actual;
	}


	public void setSize_in_MM_actual(String size_in_MM_actual) {
		this.size_in_MM_actual = size_in_MM_actual;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public Float getMid_price() {
		return mid_price;
	}


	public void setMid_price(Float mid_price) {
		this.mid_price = mid_price;
	}


	public Float getYield_perc() {
		return yield_perc;
	}


	public void setYield_perc(Float yield_perc) {
		this.yield_perc = yield_perc;
	}

//
	public Float getSpread() {
		return spread;
	}


	public void setSpread(Float spread) {
		this.spread = spread;
	}
//

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

//
	public String getVenue() {
		return venue;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}
//
//
	public String getProcess_trade() {
		return process_trade;
	}


	public void setProcess_trade(String process_trade) {
		this.process_trade = process_trade;
	}


	
	  public String getAuto_execution() { return auto_execution; }
	  
	  
	  public void setAuto_execution(String auto_execution) { this.auto_execution =
	  auto_execution; }
	 


	public String getPortfolio_trade() {
		return portfolio_trade;
	}


	public void setPortfolio_trade(String portfolio_trade) {
		this.portfolio_trade = portfolio_trade;
	}

	
	
}
