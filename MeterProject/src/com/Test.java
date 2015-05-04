package com;


public class Test {
//	public static void main(String[] args) {
//		// Step 1 - Create the DAO
//		InputDAO dao = new InputDAO();
//		try {
//
//			List list = sqlQuery();
//			System.out.println(list.size());
//
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				dao.getSession().close();
//			} catch (Exception e) {
//				// do nothing
//			}
//		}
//	}
//	
//	// example + null (ต้องใส่ value ให้ครบทุก field meaning จะถูก)
//	private static List example() {
//		Input input = new Input();
//		//input.setAvgwtusg(0);
//		Example e = Example.create(input);
//		e.excludeNone(); // search null
//		e.excludeProperty("zn"); // for don't understand right way to use
//		e.excludeProperty("br");
//		e.excludeProperty("custcode");
//		e.excludeProperty("rte");
//		e.excludeProperty("seq");
//		Criteria criteria = HibernateSessionFactory.getSession().createCriteria(Input.class);
//		criteria.add(e);
//		return criteria.list();
//	}
//
//	private static void findById(InputDAO dao) {
//		Input input = dao.findById(new InputId("5530332", "00", "1018776", "110008", "0"));
//		System.out.println("Entity retrieval successful, message is: " + input.getName());
//	}
//	
//	// ไม่ search nulls
//	private static List findExample(InputDAO dao) {
//		Input in = new Input();
//		 in.setName("นายวีระพงษ์ สุกฤตานนท์");
////		 in.setBr(null);
////		 in.setCustcode("1018776");
//		in.setCuststat(null);
//		List list = dao.findByExample(in);
//		return list;
//	}
//	
//	// use criteria + Restrictions
//	private static List criteria() {
//		Criteria c = HibernateSessionFactory.getSession().createCriteria(Input.class);
//		c.add(Restrictions.eq("br", "5530332"));
//		return c.list();
//	}
//	
//	// use q 
//	private static List  queryObject() {
//		//String queryString = "from Input as model where model.name= :name";
//		String queryString = "from Input  where name= :name";
//		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
//		queryObject.setParameter("name", "นายอัฐพร ก้อนคำ");
//		return queryObject.list();
//		
//	}
//	
//	// use sql 
//	private static List  sqlQuery() {
//		//String queryString = "from Input as model where model.name= :name";
//		String queryString = "select i.zn,i.br,i.custcode,i.rte,i.seq from Input  i";
//		SQLQuery q = HibernateSessionFactory.getSession().createSQLQuery(queryString).addEntity("i",Input.class);
//		
//		return q.list();
//		
//	}
}
