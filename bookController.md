
@WebServlet("/rent/*")
public class BookRentController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	protected BookRentService brService;
	protected BuyerService buService;
	protected BookService bkService;
	
	
	public BookRentController() {
		brService = new BookRentServiceImplV1(); // new... 추가하기
		buService = new BuyerServiceImplV1();
		bkService = new BookServiceImplV1();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
	
		String subPath = req.getPathInfo();
		System.out.println(subPath);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		if(subPath.equals("/seq")) {
			
			// 주문번호로 찾기
			String strSeq = req.getParameter("id");
			
			if(strSeq == null || strSeq.equals("")) {
				out.println("주문번호가 없음");
				out.close();
			} else {
				
				Long nSeq = Long.valueOf(strSeq);
				BookRentDTO brDTO = brService.findById(nSeq);
				
				ServletContext app = this.getServletContext();
			
				app.setAttribute("BOOK", brDTO);
				
			
				RequestDispatcher disp
				= app.getRequestDispatcher("/WEB-INF/views/order_info.jsp");
			
				disp.forward(req, resp);
			}
		}
		if(subPath.equals("/list")) {
			brService.selectAll();
			out.println("도서대여 전체목록보기");
		
		} else if(subPath.equals("/isbn")) {

			brService.findByBISBN("isbn");
		} else if(subPath.equals("/buyer")) {

			brService.findByBuyerCode("buyercode");
			
		} else if(subPath.equals("/order")) {
			RequestDispatcher disp = req.getRequestDispatcher
					("/WEB-INF/views/order.jsp");
			disp.forward(req, resp);
			
		} else if(subPath.equals("/order/page1")) {
			
			String bu_name = req.getParameter("bu_name");
			if(bu_name == null || bu_name.equals("")) {
				out.println("회원이름을 반드시 입력해야 합니다");
				out.close();
			} else {
				List<BuyerDTO> buList 
					= buService.findByName(bu_name);
				System.out.println("=".repeat(50));
				for(BuyerDTO d: buList) {
					System.out.println(d.toString());
				}
				System.out.println("=".repeat(50));
				
				req.setAttribute("BUYERS", buList);
			
				RequestDispatcher disp
				= req.getRequestDispatcher("/WEB-INF/views/page1.jsp");
				disp.forward(req, resp);
			}
		}else if(subPath.equals("/order/page2")) {
			
			String bu_code = req.getParameter("bu_code");
			// bu_code 값에 해당하는 회원정보 추출
			BuyerDTO buyerDTO = buService.findById(bu_code);
			if(buyerDTO != null) {
			
				System.out.println(buyerDTO.toString());
			}
			//ServletContext app = req.getServletContext();
			//app.setAttribute("BUYER", buyerDTO);
			req.setAttribute("BUYER", buyerDTO);
							
			RequestDispatcher disp
			= req.getRequestDispatcher("/WEB-INF/views/page2.jsp");
			disp.forward(req, resp);
		} else if(subPath.equals("/order/book")) {
			String bu_code = req.getParameter("bu_code");
			String bk_title = req.getParameter("bk_title");
			if(bk_title == null || bk_title.equals("")) {
				out.println("도서명을 입력하세요");
				out.close();
			} else {
				
				BuyerDTO buDTO = buService.findById(bu_code);
				req.setAttribute("BUYER", buDTO);
				List<BookDTO> bookList 
				= bkService.findByTitle(bk_title);
				
				req.setAttribute("BOOKS", bookList);
				
				// method chaining 방식으로 연속 호출하기
				req.getRequestDispatcher("/WEB-INF/views/book.jsp")
				.forward(req, resp);
			}
		
		} else if(subPath.equals("/order/insert")) {
			
			String bk_isbn = req.getParameter("bk_isbn");
			String bu_code = req.getParameter("bu_code");

			Date date = new Date(System.currentTimeMillis());
			
			SimpleDateFormat sd 
				= new SimpleDateFormat("yyyy-MM-dd");
			
			String sDate = sd.format(date);
			System.out.println("대여일자 : "+ sDate);
			
		
			BookRentVO brVO = new BookRentVO();
			brVO.setBr_sdate(sDate);
			brVO.setBr_isbn(bk_isbn);
			brVO.setBr_bcode(bu_code);
			brVO.setBr_price(1000);
			
			int result = brService.insert(brVO);
			if(result > 0) {
				out.println("대여정보 추가 성공!!!");
			} else {
				out.println("대여정보 추가 실패!!!");
			}
			out.close();
			
		} else if(subPath.equals("/return")) {
			// 반납하기
			BookRentVO bookRentVO = new BookRentVO();
			brService.update(bookRentVO);
		} else {
			out.println("NOT FOUND");
			// 더이상 그만하기
		}
	}

}