package Controller.API;

import DTO.CctransactionDTO;
import DTO.OrderProductDTO;
import DTO.UserAccountDTO;
import Model.CcTransactionsEntity;
import Model.OrderProductsEntity;
import Model.SalesOrdersEntity;
import Utils.ApplicationUtils;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderAPI", value = "/OrderAPI")
public class OrderAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if(userAccountDTO!=null) {
            List<CcTransactionsEntity> ccTransactionsEntityList=SingletonServiceUltils.getCcTransactionDAOImpl().getAllbyUserIdandStatus(userAccountDTO.getId());
            this.loadOrderAPI(response, ccTransactionsEntityList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void loadOrderAPI(HttpServletResponse response,List<CcTransactionsEntity> ccTransactionsEntityList) throws IOException
    {
        List<CctransactionDTO> cctransactionDTOList = this.getCcTransactionDTOList( ccTransactionsEntityList);

        try (PrintWriter out = response.getWriter()) {
            for (int i=0;i<cctransactionDTOList.size();i++) {
               SalesOrdersEntity salesOrdersEntity=SingletonServiceUltils.getSalesOrderDAOImpl().getOnebyId(cctransactionDTOList.get(i).getOrder_id());
               long sum=SingletonServiceUltils.getOrderProductDAOImpl().sumQuantitybySaleId(salesOrdersEntity.getId());
                out.println("<tr>\n" +
                        "     <td>"+i+ "</td>\n" +
                        "     <td>"+cctransactionDTOList.get(i).getTransdate()+"</td>\n" +
                        "     <td><span class=\"success\">"+cctransactionDTOList.get(i).getStatus()+"</span></td>\n" +
                        "     <td>"+"$"+cctransactionDTOList.get(i).getAmoumt()+" For "+sum+" Item "+"</td>\n" +
                        "     <td><a href=\"/CheckOutController?cartid="+salesOrdersEntity.getId()+"\" class=\"view\">view</a></td>\n" +
                        "     </tr> ");
            }
        }

    }
    private List<CctransactionDTO> getCcTransactionDTOList(List<CcTransactionsEntity> ccTransactionsEntityList)
    {
        List<CctransactionDTO> cctransactionDTOList=new ArrayList<>();

        for (CcTransactionsEntity cc: ccTransactionsEntityList
        ) {
            cctransactionDTOList.add(this.handleDataOneCctransaction(cc));
        }
        return  cctransactionDTOList;
    }
    private CctransactionDTO handleDataOneCctransaction(CcTransactionsEntity ccTransactionsEntity)
    {
        CctransactionDTO cc=new CctransactionDTO();
        int id=ccTransactionsEntity.getSalesOrdersEntity().getId();
        String status=ccTransactionsEntity.getStatus();
        Date transdate=ccTransactionsEntity.getTransdate();
        BigDecimal amount=ccTransactionsEntity.getAmount();
        cc.setOrder_id(id);
        cc.setStatus(status);
        cc.setAmoumt(amount);
        cc.setTransdate(transdate);
        return  cc;
    }
}
