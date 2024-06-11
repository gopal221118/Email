package com.email.html;

public class Insurance_HTML {
	
	public static String getMailHTML(String name,String licno,String address) {
		String htmlContent="";
		try
		{
		htmlContent = "<html><head><style>" + "body {font-family: Arial, sans-serif; line-height: 1.6;}"
				+ "h1 {color: #007bff;}" + "p {margin-bottom: 10px;}" + ".unsubscribe {color: #dc3545;}"
				+ "@media screen and (max-width: 600px) {"
				+ ".centered-image {display: block; margin-left: auto; margin-right: auto; width: 80%;}"
				+ "}</style></head><body>" + "<p>Dear <strong>"+name+"</strong>,</p>"
				+ "<p>As a proud holder of the FSSAI License with <strong>"+licno+"</strong>, we know how much effort you put into running your business at <strong>["+address+"]</strong> .Keeping it safe must be your utmost priority, but it can be tough to know where to start. That's why we're reaching out to recommend safeguarding your enterprise with comprehensive Shop Insurance.</p>"
				+ "<p>Unexpected things like fire accidents, theft, and burglaries can throw a wrench in your operations. Our Shop Insurance offers robust protection against a range of potential risks, ensuring your hard work and investment are shielded from financial setbacks. For less than Re. 1 per day, you can protect what you've built.</p>"
				+ "<p>Don't wait for trouble to knock on your door. Take proactive steps to secure your future success. Click the link below to explore your Shop Insurance options and take the first step towards safeguarding your business today.</p></br>"
				+ "<div style=\"text-align:center\">" + "<a href=\"https://staging.indiaadvocacy.com/Insurance.php\">"
				+ "<img class=\"centered-image\" src=\"https://img.hotimg.com/Frame-1ded49e93ce072781.png\" alt=\"Frame-1ded49e93ce072781.png\" border=\"0\">"
				+ "</a></div></br></br>"
				+ "<center><p>Contact us at <a href=\"mailto:care@indiaadvocacy.in\">support@indiaadvocacy.in</a> for any assistance or feel free to visit our <a href=\"https://indiaadvocacy.in/\">website</a> to avail other services.</p>"
				+ "<p class=\"unsubscribe\"><a href=\"mailto:unsubscribe@indiaadvocacy.in?subject=Unsubscribe&body=Please%20unsubscribe%20me%20from%20further%20communications\">Unsubscribe</a> from further communications.</p></center>"
				+ "<br><center><small><i>Powered by <a class=\"gmail_campaigns_install_button\" href=\"https://www.indiaadvocacy.in/\">India Advocacy : An MsME Compliance MarketPlace</a></i></small></center><br>"
				+ "</body></html>";
		System.out.println(htmlContent);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return htmlContent;
	}

}
