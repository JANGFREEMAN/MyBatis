package mybatis.test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.DocFlavor.READER;

import mybatis.map.UserInter;
import mybatis.pojo.Author;
import mybatis.pojo.User;
import mybatis.pojo.Visit;
import mybatis.utils.MyBatisUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MybatisTest {
	
	@Test
	public void selectTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = session.selectOne("findUserByID",1);
			System.out.println(user.getUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectTest2(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "2");
			User user = session.selectOne("findUserByID2",map);
			System.out.println(user.getUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectListTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<User> users = session.selectList("getAllUsers");
			for(User user : users){
				System.out.println(user.getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectListTest2(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<User> users = session.selectList("getAllUsers2");
			for(User user : users){
				System.out.println(user.getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	
	
	@Test
	public void insertTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
			for(int i = 0 ; i < 20 ; i ++){
				user.setUserName("张三"+i);
				user.setPassword(""+new Random(10).nextInt());
				session.insert("insertUser", user);
				session.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void updateTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
			user.setId(2);
			user.setUserName("zhangyongxiang");
			user.setPassword("freeman");
			session.update("updateUser", user);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void deleteTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			UserInter userInt = session.getMapper(UserInter.class);
			userInt.deleteUser(1);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void transactionTest(){
		String resource = "mybatis/map/MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession(false);//开启事物
			User user = new User();
			user.setPassword("zhaoliu");
			user.setUserName("zhaoliu");
			session.insert("insertUser", user);
			System.out.println("new userID:"+user.getId());
			Author author = new Author();
			author.setUser(user);
			author.setRealName("赵六");
			author.setIDCard(111);
			session.insert("insertAuthor",author);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
	}
	@Test
	public void selectAuthorJoinTest(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<Author> authors = session.selectList("selectAuthorJoin");
			for(Author author : authors){
				System.out.println(author.getUser().getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectAuthorJoinTest2(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<Author> authors = session.selectList("selectAuthorJoin2");
			for(Author author : authors){
				System.out.println(author.getUser().getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Test
	public void selectAuthorJoinTest3(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<Author> authors = session.selectList("selectAuthorJoin3");
			for(Author author : authors){
				System.out.println(author.getUser().getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectVisitsTest(){//集合查询
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			List<User> users = session.selectList("getVisitsLists");
			for(User user : users){
				for(Visit visit : user.getVisits()){
					System.out.println(visit.getVisitIP());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectReaderTest(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			mybatis.pojo.Reader reader = new mybatis.pojo.Reader();
			reader.setMoney((float) 11.11);
			List<mybatis.pojo.Reader> readers = session.selectList("selectReadersByDy",reader);
			for(mybatis.pojo.Reader reader2 : readers){
				System.out.println(reader2.getMoney());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectReaderTest2(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			mybatis.pojo.Reader reader = new mybatis.pojo.Reader();
			reader.setMoney((float) 11.11);
			List<mybatis.pojo.Reader> readers = session.selectList("selectReadersByDy2",reader);
			for(mybatis.pojo.Reader reader2 : readers){
				System.out.println(reader2.getUser().getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectUserByDyTest(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
//			user.setUserName("wangwu");
			user.setPassword("wangwu");
			List<User> users = session.selectList("selectUserByDy",user);
			for(User user2 : users){
				System.out.println(user2.getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	
	@Test
	public void selectUserByDy2Test(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
			user.setUserName("wangwu");
			user.setPassword("wangwu");
			List<User> users = session.selectList("selectUserByDy2",user);
			for(User user2 : users){
				System.out.println(user2.getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	public void selectUserByDy3Test(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
			user.setId(2);
			user.setUserName("11");
			user.setPassword("11");
			List<User> users = session.selectList("selectUserByDy3",user);
			for(User user2 : users){
				System.out.println(user2.getUserName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Test
	public void selectUserByDy4Test(){//动态sql
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			User user = new User();
			user.setId(2);
			user.setUserName("ww");
			user.setPassword("ww");
			session.update("selectUserByDy4",user);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
}
