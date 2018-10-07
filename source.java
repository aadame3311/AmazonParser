//Antonio Adame
//Gets product data from amazon.com based on its ASIN
//then uploads that product information for sale on ebay.com

//Meant to automate the task of selling Amazon products on Ebay. 

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//input imports
import java.util.Scanner;


public class source {
	public static void main(String[] args) throws IOException {
		String description = "";
		
		
		//initialize scanner
		Scanner in = new Scanner(System.in);
		Document doc = null;
		
		System.out.print("Please input ASIN number: ");
		String ASIN = in.nextLine();
		System.out.println();
		
		//connect to link using the ASIN number, gather product title. 
		System.out.println("Loading Product...");
		try {
			doc = Jsoup.connect("https://www.amazon.com/gp/product/"+ ASIN).get();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Product Not Found");
		}

		
		if (doc != null) {
			//gather product title. 
			Elements product_name = doc.select("span#productTitle");
			//gather product image. 
			Elements image_tags = doc.select("img#landingImage");
			//gather product descriptions. 
			Elements desc_list = doc.select("div#feature-bullets");
			Elements prod_descriptions = desc_list.select("span.a-list-item");
			
			System.out.println();
			
			//display product title. 
			for (Element title : product_name) 
				System.out.println("Product Name:             " + title.text());
			//display product image URL.
			for (Element img : image_tags) {
				
				String image_url = img.attr("data-old-hires");
				if (image_url.equals("")) {
					image_url="N/A";
				}
				
				System.out.println("Image URL:                " + image_url);
			}
			//display product description. 
			for (Element desc : prod_descriptions) {
				description+=desc.text()+"\n";
				//System.out.println("Product Description:      " + desc.text());
			}
			System.out.println("Product Description: \n" + description);
			in.close();
		}
		
		
		
	}
	
	
	
}
