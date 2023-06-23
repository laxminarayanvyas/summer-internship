package com.fintech.services;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.OutputFile;
import com.opencsv.CSVWriter;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class ExcelHelper {

	public static ByteArrayInputStream OPFileToCSV(List<OutputFile> files) {

		String[] HEADERs = { "DATE", "TIME(UTC)", "NO. DEALERS", "SIDE", "ISIN", "TICKER", "MATURITY", "COUPON (%)",
				"SIZE", "CCY", "PRICE", "MID PRICE", "YIELD (%)", "SPREAD (%)", "SETTLEMENT", "ON VENUE", "VENUE",
				"PROCESS TRADE", "AUTOEX", "PORTFOLIO TRADE" };

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(out))) {

			// Write CSV header
			csvWriter.writeNext(HEADERs);

			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Change the date format as
																							// per your requirement
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String tradeDateStr;
			// LocalDate tradeDate = file.getTrade_date();
			for (OutputFile file : files) {
				String[] rowData = {
						(LocalDate.parse(file.getTrade_date().toString(), inputFormatter)).format(outputFormatter),
						// String.format("%04d-%02d-%02d", file.getTrade_date().getYear(),
						// file.getTrade_date().getMonthValue(), file.getTrade_date().getDayOfMonth()),
						// file.getTrade_date().format(outputFormatter),
						file.getTrade_time().format(timeFormatter),
						file.getDealers_in_competition() == null ? "" : file.getDealers_in_competition().toString(),
						file.getSide() == null ? "" : file.getSide(), file.getIsin() == null ? "" : file.getIsin(),
						file.getTicker() == null ? "" : file.getTicker(), file.getMaturity().format(outputFormatter),
						file.getCoupon_perc() == null ? "" : file.getCoupon_perc().toString(),
						file.getSize_in_MM_actual() == null ? "" : file.getSize_in_MM_actual().toString(),
						file.getCurrency() == null ? "" : file.getCurrency(),
						file.getPrice() == null ? "" : file.getPrice().toString(),
						file.getMid_price() == null ? "" : file.getMid_price().toString(),
						file.getYield_perc() == null ? "" : file.getYield_perc().toString(),
						file.getSpread() == null ? "" : file.getSpread().toString(),
						file.getSettlement_date().format(outputFormatter),
						file.getOn_venue() == null ? "" : file.getOn_venue(),
						file.getVenue() == null ? "" : file.getVenue(),
						file.getProcess_trade() == null ? "" : file.getProcess_trade(),
						file.getAuto_execution() == null ? "" : file.getAuto_execution(),
						file.getAuto_execution() == null ? "" : file.getAuto_execution() }; // Replace with your data

				csvWriter.writeNext(rowData);
			}

			csvWriter.flush();

			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Failed to generate CSV file: " + e.getMessage());
		}
	}

	// for consolidated file op

	public static Stream<String> consolidatedOP(List<ConsolidatedOPFile> dataList) throws IOException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Change the date format as per your
																					// requirement
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		Stream<String> csvLines = dataList.stream().map(file -> {
			String[] rowData = { 
					file.getOutput_file_dtl_id().toString(),
					file.getProcess_guid() == null ? "" : file.getProcess_guid(),
					file.getClubbed_trades_count().toString() == null ? "" : file.getClubbed_trades_count().toString(),
					file.getTrade_date().format(formatter), file.getTrade_time().format(timeFormatter),
					file.getDealers_in_competition() == null ? "" : file.getDealers_in_competition().toString(),
					file.getSide() == null ? "" : file.getSide(), file.getIsin() == null ? "" : file.getIsin(),
					file.getTicker() == null ? "" : file.getTicker(),
					file.getMaturity() == null ? "" : file.getMaturity().format(formatter),
					file.getCoupon_perc() == null ? "" : file.getCoupon_perc().toString(),
					file.getSize_in_MM_actual() == null ? "" : file.getSize_in_MM_actual().toString(),
					file.getSize_in_MM() == null ? "" : file.getSize_in_MM(),
					file.getCurrency() == null ? "" : file.getCurrency(),
					file.getPrice_actual() == null ? "" : file.getPrice_actual().toString(),
					file.getPrice() == null ? "" : file.getPrice().toString(),
					file.getMid_price_actual() == null ? "" : file.getMid_price_actual().toString(),
					file.getMid_price() == null ? "" : file.getMid_price().toString(),
					file.getYield_perc() == null ? "" : file.getYield_perc().toString(),
					file.getSpread() == null ? "" : file.getSpread().toString(),
					file.getSettlement_date() == null ? "" : file.getSettlement_date().format(formatter),
					file.getOn_venue() == null ? "" : file.getOn_venue(),
					file.getVenue_actual() == null ? "" : file.getVenue_actual(),
					file.getVenue_mic() == null ? "" : file.getVenue_mic(),
					file.getVenue_identifier() == null ? "" : file.getVenue_identifier(),
					file.getProcess_trade() == null ? "" : file.getProcess_trade(),
					file.getAuto_execution() == null ? "" : file.getAuto_execution(),
					file.getPortfolio_trade() == null ? "" : file.getPortfolio_trade(),
					file.getBuy_side() == null ? "" : file.getBuy_side(),
					file.getBuy_side_lei() == null ? "" : file.getBuy_side_lei(),
					file.getClient_unique_key() == null ? "" : file.getClient_unique_key(),
					file.getCounter_party() == null ? "" : file.getCounter_party(),
					file.getCounter_party_lei() == null ? "" : file.getCounter_party_lei(),
					file.getCounterparty_identifier() == null ? "" : file.getCounterparty_identifier(),
					file.getSecmst_entity_name() == null ? "" : file.getSecmst_entity_name(),
					file.getSecmst_ticker() == null ? "" : file.getSecmst_ticker(),
					file.getSecmst_sector() == null ? "" : file.getSecmst_sector(),
					file.getSecmst_country() == null ? "" : file.getSecmst_country(),
					file.getSecmst_issue_date() == null ? "" : file.getSecmst_issue_date().format(formatter),
					file.getSecmst_maturity_date() == null ? "" : file.getSecmst_maturity_date().format(formatter),
					file.getSecmst_issue_currency() == null ? "" : file.getSecmst_issue_currency(),
					file.getSecmst_issuance_coupon() == null ? "" : file.getSecmst_issuance_coupon().toString(),
					file.getSecmst_coupon_type() == null ? "" : file.getSecmst_coupon_type(),
					file.getSecmst_bond_type() == null ? "" : file.getSecmst_bond_type(),
					file.getSecmst_amount_issued() == null ? "" : file.getSecmst_amount_issued().toString(),
					file.getSecmst_seniority() == null ? "" : file.getSecmst_seniority(),
					file.getSecmst_credit_grade() == null ? "" : file.getSecmst_credit_grade(),
					file.getInternal_cross() == null ? "" : file.getInternal_cross(),
					file.getFill_ratio() == null ? "" : file.getFill_ratio().toString(),
					file.getClubbed_trade_ids() == null ? "" : file.getClubbed_trade_ids(),
					file.getIs_active() == null ? "" : file.getIs_active().toString(),
					file.getFile_name() == null ? "" : file.getFile_name(),
					file.getFile_generation_date() == null ? "" : file.getFile_generation_date().format(formatter),
					file.getEntry_by() == null ? "" : file.getEntry_by().toString(),
					file.getEntry_dt_time() == null ? "" : file.getEntry_dt_time().format(formatter),
					file.getUpdated_date() == null ? "" : file.getUpdated_date().format(formatter),
					file.getSize_in_MM_capped_num() == null ? "" : file.getSize_in_MM_capped_num().toString() };

			return String.join(",", rowData);
		});

		return Stream.concat(Stream.of(getHeaderRow()), csvLines);
	}
	private static String getHeaderRow() {
		String[] HEADERs = { "output_file_dtl_id", "process_guid", "clubbed_trades_count", "trade_date", "trade_time",
				"dealers_in_competition", "side", "isin", "ticker", "maturity", "coupon_perc", "size_in_MM_actual",
				"size_in_MM", "currency", "price_actual", "price", "mid_price_actual", "mid_price", "yield_perc",
				"spread", "settlement_date", "on_venue", "venue_actual", "venue_mic", "venue_identifier",
				"process_trade", "auto_execution", "portfolio_trade", "buy_side", "buy_side_lei", "client_unique_key",
				"counter_party", "counter_party_lei", "counterparty_identifier", "secmst_entity_name", "secmst_ticker",
				"secmst_sector", "secmst_country", "secmst_issue_date", "secmst_maturity_date", "secmst_issue_currency",
				"secmst_issuance_coupon", "secmst_coupon_type", "secmst_bond_type", "secmst_amount_issued",
				"secmst_seniority", "secmst_credit_grade", "internal_cross", "fill_ratio", "clubbed_trade_ids",
				"is_active", "file_name", "file_generation_date", "entry_by", "entry_dt_time", "updated_date",
				"size_in_MM_capped_num" };
	    
	    return String.join(",", HEADERs);
	}

}
