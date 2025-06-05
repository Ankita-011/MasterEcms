package org.test.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

	    public static void readAndValidateCSV(File csvFile, String mobileNumberFilter, String employeeIdFilter,
	                                          String transactionIdFilter, String approvalStatusFilter) throws IOException {

	        BufferedReader br = new BufferedReader(new FileReader(csvFile));
	        String line;
	        boolean headerSkipped = false;

	        while ((line = br.readLine()) != null) {

	            // Skip header
	            if (!headerSkipped) {
	                headerSkipped = true;
	                continue;
	            }

	            // Safely split and check array length
	            String[] values = line.split(",");

	            if (values.length > 52) {
	                String mobile = values[0].trim();
	                String empId = values[1].trim();
	                String trxnId = values[2].trim();
	                String status = values[5].trim();  // ← your target field

	                // Sample validation
	                if ((mobileNumberFilter != null && !mobileNumberFilter.isEmpty() && !mobile.contains(mobileNumberFilter)) ||
	                    (employeeIdFilter != null && !employeeIdFilter.isEmpty() && !empId.equals(employeeIdFilter)) ||
	                    (transactionIdFilter != null && !transactionIdFilter.isEmpty() && !trxnId.equals(transactionIdFilter)) ||
	                    (approvalStatusFilter != null && !approvalStatusFilter.isEmpty() && !status.equalsIgnoreCase(approvalStatusFilter))) {

	                    System.out.println("❌ Mismatch found in line: " + line);
	                } else {
	                    System.out.println("✅ Match found: " + line);
	                }

	            } else {
	                // Prevent crash from short or malformed rows
	                System.out.println("⚠️ Skipping malformed or incomplete line: " + line);
	            }
	        }

	        br.close();
	    }
	}

	


