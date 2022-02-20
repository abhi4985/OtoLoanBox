package api;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class ApiPaths {
	
	public static String base_path  ="http://int-backend-stage.oto.com/";
	public static String sfa_base_path ="http://int-sfa-stage.oto.com/";
	public static String market_mrp_cars  ="api/v2/market-mrp-cars";

    public static String send_otp  ="loan/user/send_otp";
    public static String login_otp  ="loan/user/login-otp";
    public static String email_login ="account/user/login";
    public static String all_mmv = "core/commonservice/mmv_all";
    public static String loan_summary = "loan/lead/loan_summary";
    //inventory
    public static String loan_stock = "inventory/inventory/stocklist";
    
   //rule_engine
    public static String sync_mmv = "loan/rule_engine/sync_mmv";
    public static String get_model_code = "loan/rule_engine/get_model_code";
    
    //Lead
    public static String addlead = "loan/lead/addlead";
    public static String savedoc = "loan/lead/save_doc";
    
    //Quote
    public static String multi_quote_form="loan/quote/quote_form_fields";
    public static String multi_getquotes="loan/quote/get_quotes";
    public static String get_config="loan/config/get";
    public static String calculate_loan="loan/rule_engine/calculate_loan";
    
    
    
    //Header
    public static String source_gcloud="GCLOUD";
    public static String source_sfa="sfa";
    public static String key_sfa="SFAUQPlnGed0dQ8iWosxptjSbUJOJ1NEQ";
    
    public static Headers getHeaders() {
    	Header h1= new Header("Accept", "*/*");
		Header h2 = new Header("Accept-Language", "en-US");
		Header h3 = new Header("User-Agent", "PostmanRuntime/7.28.4");
		Header h4= new Header("Content-Type", "application/json");
		Header h5= new Header("Host", "int-gcloud-stage.oto.com");
		Header h6= new Header("Content-Length", "<calculated when request is sent>");
		Header h7= new Header("Accept-Encoding", "gzip, deflate, br");
		Header h8= new Header("Connection", "keep-live");

		List<Header> list = new ArrayList<Header>();
		list.add(h1);
		list.add(h2);
		list.add(h3);
		list.add(h4);
		list.add(h5);
		//  list.add(h6);
	    //	list.add(h7);
		//  list.add(h8);
		Headers headers = new Headers(list);
		return headers;
    }
}
