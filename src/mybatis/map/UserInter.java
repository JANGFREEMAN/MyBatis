package mybatis.map;

import org.apache.ibatis.annotations.Delete;

public interface UserInter {

	@Delete("delete from user where id = #{id}")
	public void deleteUser(int id);
}
