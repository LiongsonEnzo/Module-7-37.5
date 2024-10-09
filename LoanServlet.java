import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the parameters from the form
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numOfYears = Integer.parseInt(request.getParameter("numOfYears"));

        // Use Loan class to calculate the monthly and total payments
        Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Output the result
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Loan Details</h2>");
        out.println("Loan Amount: $" + loanAmount + "<br>");
        out.println("Annual Interest Rate: " + annualInterestRate + "%<br>");
        out.println("Number of Years: " + numOfYears + "<br>");
        out.println("<h3>Results</h3>");
        out.println("Monthly Payment: $" + String.format("%.2f", monthlyPayment) + "<br>");
        out.println("Total Payment: $" + String.format("%.2f", totalPayment) + "<br>");
        out.println("</body></html>");
    }
}
