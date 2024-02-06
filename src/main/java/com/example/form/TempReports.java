package com.example.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TempReports {
	private final TempRepairingForm tempRepairingForm;
	private final List<TempUsedItemsReportForm> tempUsedItemsList;
	
	public TempReports() {
			this.tempRepairingForm = new TempRepairingForm();
			this.tempUsedItemsList = new ArrayList<>();
			for(int i=0;i<50;i++) {
				this.tempUsedItemsList.add(new TempUsedItemsReportForm());
			}
	}
}
