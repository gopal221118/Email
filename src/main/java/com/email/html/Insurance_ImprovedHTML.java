package com.email.html;

public class Insurance_ImprovedHTML {

	public static String getMailHTML(String name, String licno, String address) {
	    String htmlContent = "";
	    try {
	        htmlContent = "<html><head><style>" +
	                "body {font-family: Arial, sans-serif; line-height: 1.6;}" +
	                "h1 {color: #007bff;}" +
	                "p {margin-bottom: 10px;}" +
	                ".unsubscribe {color: #dc3545;}" +
	                "@media screen and (max-width: 600px) {" +
	                ".centered-image {display: block; margin-left: auto; margin-right: auto; width: 80%;}" +
	                "}</style></head><body>" +
	                "<p>Dear <strong>" + toCamelCase(name) + "</strong>,</p>" +
	                "<p>We appreciate your dedication to maintaining high standards for your business at <strong>" + toCamelCase(address) + "</strong>. As a proud holder of the FSSAI License (Lic. No. <strong>" + licno + "</strong>), you know the importance of safeguarding your enterprise.</p>" +
	                "<p>To help protect your business from unforeseen events such as fire, theft, and burglary, we recommend considering our comprehensive Shop Insurance. For less than Re. 1 per day, you can ensure your business is protected from potential financial setbacks.</p>" +
	                "<p>Taking proactive steps today can secure your future success. To learn more about our Shop Insurance options, please click the link below. Our insurance plan offers robust coverage, giving you peace of mind and allowing you to focus on growing your business.</p></br>" +
	                "<div style=\"text-align:center\">" +
	                "<a href=\"https://staging.indiaadvocacy.com/Insurance.php\">" +
	                "<img class=\"centered-image\" src=\"https://img.hotimg.com/Frame-1ded49e93ce072781.png\" alt=\"Shop Insurance\" border=\"0\">" +
	                "</a></div></br></br>" +
	                "<center><p>Contact us at <a href=\"mailto:support@indiaadvocacy.in\">support@indiaadvocacy.in</a> for any assistance or feel free to visit our <a href=\"https://indiaadvocacy.in/\">website</a> to avail other services.</p>" +
	                "<p class=\"unsubscribe\"><a href=\"mailto:unsubscribe@indiaadvocacy.in?subject=Unsubscribe&body=Please%20unsubscribe%20me%20from%20further%20communications\">Unsubscribe</a> from further communications.</p></center>" +
	                "<br><center><small><i>Powered by <a class=\"gmail_campaigns_install_button\" href=\"https://www.indiaadvocacy.in/\">India Advocacy: An MsME Compliance Marketplace</a></i></small></center><br>" +
	                "</body></html>";
	        System.out.println(htmlContent);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return htmlContent;
	}
	
	public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder camelCase = new StringBuilder();
        boolean nextCharUpperCase = false;

        for (char currentChar : input.toCharArray()) {
            if (Character.isSpaceChar(currentChar) || currentChar == '_') {
                nextCharUpperCase = true;
            } else {
                if (nextCharUpperCase) {
                    camelCase.append(Character.toUpperCase(currentChar));
                    nextCharUpperCase = false;
                } else {
                    camelCase.append(Character.toLowerCase(currentChar));
                }
            }
        }

        return camelCase.toString();
    }
}


