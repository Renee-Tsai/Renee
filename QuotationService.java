package Service;

import java.util.ArrayList;
import java.util.List;


import EK.Quotation;


public class QuotationService {

	public List<Quotation> getQuotation(List<String>Data){
		ArrayList<Quotation> quotationList = new ArrayList<Quotation>();
		for(int i = 0 ; i < Data.size() ; i++ ) {
			String[] tokens = Data.get(i).split(",");
			System.out.println(tokens[0]+":"+tokens[1]);
			Quotation quotation = new Quotation();
			quotation.setNumber(Integer.parseInt(tokens[0]));
			quotation.setUseway(tokens[1]);
			quotation.setBrand(tokens[2]);
			quotation.setSize(tokens[3]);
			quotation.setFr(tokens[4]);
			quotation.setLoadAndSpeed(tokens[5]);
			quotation.setPrice(Integer.parseInt(tokens[6]));;
			quotation.setNote(tokens[7]);
			System.out.println(quotation.toString());
			quotationList.add(quotation);
 		}
		
		return quotationList;
	}

}

