package com.chao.helper.db.dao;


import com.chao.helper.beans.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色操作的实体类
 * @author zhangge
 */
@Repository
public class RoleDao extends BaseDao<Role>{

	/**
	 * 添加角色使用SQL
	 */
	private static final String ADD_ROLE = "INSERT INTO _roles(_roleName, _createTime, _updateTime, _status) "
			+ "	VALUES(?, now(), now(), ?)";
	
	/**
	 * 更新角色信息使用SQL
	 */
	private static final String UPDATE_ROLE = "UPDATE _roles SET _roleName =?, _updateTime = now() WHERE _id = ?";
	
	/**
	 * 给角色赋权限使用SQL
	 */
	private static final String ADD_ROLE_PERMISSION = "INSERT INTO _roles_permissions(_roleId, _authId, _status)"
			+ " VALUES(?, ?, ?)";
	
	/**
	 * 删除指定角色对应的所有权限
	 */
	private static final String DEL_ROLE_PERMISSON = "DELETE FROM _roles_permissions WHERE _roleId = ?";
	
	/**
	 * 根据id查询角色信息
	 */
	private static final String QUERY_BY_ID = "SELECT _name name, _createDate createDate, _updateDate updateDate FROM _roles WHERE _id = ?";
	
	/**
	 * 查询角色的所有权限
	 */
	private static final String QUERY_ROLE_PERMISSONS = " SELECT DISTINCT _method method FROM _permissions p, _roles_permissions rp "
			 +" WHERE  rp._authId = p._id AND rp._roleId = ? and p._method <> ''";
	/**
	 * 查询代理商的权限
	 */
	private static final String QUERY_AGUSER_PERMISSONS = " SELECT DISTINCT _method method FROM _permissions p "
			 +" WHERE  p._method <> '' AND p._id like '2%'";
	/**
	 * 回显权限树 sql
	 */
	private static final String QUERY_SHOW_CHECKED = " SELECT p._id id, p._parentId pId, p._authName name, "
			 + " CASE WHEN rp._id is null then 'false' else 'true' end  checked "
			 + " FROM _permissions p LEFT JOIN _roles_permissions rp ON p._id = rp._authId "
			 + " AND rp._roleId = ? where p._platType = 1 ";
	
	/**
	 * 查询所有权限 sql
	 */
	private static final String QUERY_ALL_TREE = "select  _id id, _parentId pId, _name name, CASE WHEN _type = 0 then 'false' else 'true' end open"
			+ "  FROM _permissions ";
	
	/**
	 * 平台菜单显示查询
	 */
	private static final String QUERY_MEUN_BY_ROLEID =
			" SELECT "
			+ " p._id id, p._parentId pId, p._authName text,  p._sort  sort, p._path url  "
			+ " FROM "
			+ "_permissions p INNER JOIN _roles_permissions rp  ON p._id =  rp._authId "
			+" WHERE "
			+ "(p._type = 2 OR p._type = 1 ) AND rp._roleId = ?  and p._platType = 1 order by p._sort ";
	/**
	 * 代理菜单显示查询
	 */
	private static final String QUERY_MEUN_AGENT =
			" SELECT "
			+ " p._id id, p._parentId pId, p._authName text,  p._sort  sort, p._path url  "
			+ " FROM "
			+ "_permissions p  "
			+ " WHERE "
			+ "(p._type = 2 OR p._type = 1 ) and p._platType = 2 order by p._sort ";
	/**
	 * 代理菜单显示查询
	 */
	private static final String QUERY_MEUN_CH =
			" SELECT "
			+ " p._id id, p._parentId pId, p._authName text,  p._sort  sort, p._path url  "
			+ " FROM "
			+ "_permissions p  "
			+ " WHERE "
			+ "(p._type = 2 OR p._type = 1 ) and p._platType = 3 ";
	/**
	 * 角色分页查询sql
	 */
	private static final String QUERY_ALL_ROLE = "SELECT _id id, _roleName name, _createTime createTime, _updateTime updateTime FROM _roles ";
	
	/**
	 * 角色分页数量查询sql
	 */
	private static final String QUERY_ALL_ROLE_COUNT = "SELECT _id, _roleName, _createTime, _updateTime  FROM _roles ";
	
	/**
	 * 查询所有角色用于select2
	 */
	private static final String QUERY_ALL_ROLES = " SELECT _id id, _roleName text FROM _roles WHERE _status = 1 ";
	
	/**
	 * 查询所有可用的角色用户select2显示
	 * @return
	 */
	public List<Map<String, Object>> queryAllRoles(){
		return dbUtilsTemplate.searchToMapList(QUERY_ALL_ROLES);
	}
	
	/**
	 * 角色分页查询
	 * @param roleName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> queryAllRole(String roleName, Integer pageNo, Integer pageSize) throws Exception{
		StringBuffer whereSql = new StringBuffer(" WHERE 1 = 1 ");
		if (StringUtils.isNotEmpty(roleName)) {
			whereSql.append(" AND _roleName LIKE '%" + roleName + "%' ");
		}
		whereSql.append(" LIMIT ?, ? ");
		return dbUtilsTemplate.searchToMapList(QUERY_ALL_ROLE + whereSql, new Object[] { (pageNo - 1) * pageSize, pageSize });
	}
	
	/**
	 * 角色分页查询总数量
	 * @param roleName
	 * @return
	 */
	public long queryAllRoleCount(String roleName){
		StringBuffer whereSql = new StringBuffer(" WHERE 1 = 1 ");
		if (StringUtils.isNotEmpty(roleName)) {
			whereSql.append(" AND _roleName LIKE '%" + roleName + "%' ");
		}
		return dbUtilsTemplate.searchTotal( "SELECT COUNT(0) FROM ("+QUERY_ALL_ROLE_COUNT + whereSql +") A");
	}
	
	/**
	 * 查询角色对应的所有菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryMeunByRoleId(long roleId) throws Exception{
		return dbUtilsTemplate.searchToMapList(QUERY_MEUN_BY_ROLEID, new Object[]{roleId});
	}
	/**
	 * 查询代理角色对应的所有菜单
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryMeun_agent() throws Exception{
		return dbUtilsTemplate.searchToMapList(QUERY_MEUN_AGENT);
	}
	/**
	 * 查询代理角色对应的所有菜单
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryMeun_ch() throws Exception{
		return dbUtilsTemplate.searchToMapList(QUERY_MEUN_CH);
	}
	/**
	 * 查询所有的权限显示
	 * @throws Exception 
	 */
	public List<Map<String,Object>> queryAllTree() throws Exception{
		return dbUtilsTemplate.searchToMapList(QUERY_ALL_TREE);
	}
	
	/**
	 * 回显权限树
	 * @param roleId
	 * @throws Exception 
	 */
	public List<Map<String,Object>> queryShowChecked(long roleId){
		return dbUtilsTemplate.searchToMapList(QUERY_SHOW_CHECKED, new Object[]{roleId});
	}
	
	/**
	 * 根据角色id查询权限菜单的method属性
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	public Set<String> queryRolePermissions(long roleId) throws Exception{
		Set<String> set = new HashSet<>();
		List<Object[]> list = dbUtilsTemplate.searchToList(QUERY_ROLE_PERMISSONS, roleId);
		for (Object[] objs : list){
			if (null != objs[0]){
				set.add(objs[0].toString());
			}
		}
		return set;
	}
	/**
	 * 根据角色id查询权限菜单的method属性
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public Set<String> queryAgUserPermissions() throws Exception{
		Set<String> set = new HashSet<>();
		List<Object[]> list = dbUtilsTemplate.searchToList(QUERY_AGUSER_PERMISSONS);
		for (Object[] objs : list){
			if (null != objs[0]){
				set.add(objs[0].toString());
			}
		}
		return set;
	}
	/**
	 * 查询id对应的role信息
	 * @param role
	 * @return
	 * @throws Exception 
	 */
	public Role queryRoleById(Role role) throws Exception{
		return findOneBy(QUERY_BY_ID, new Object[]{role.getId()});
	}
	
	/**
	 * 角色添加
	 * @param role
	 */
	public void addRole(Role role){
		dbUtilsTemplate.update(ADD_ROLE, new Object[]{role.getName(),Role.State.STATE.getCode()});
	}
	
	/**
	 * 角色修改
	 * @param role
	 */
	public void updateRole(Role role){
		dbUtilsTemplate.update(UPDATE_ROLE, new Object[]{role.getName(), role.getId()});
	}
	
	public void batchInserr(Object[][] params){
		dbUtilsTemplate.batch(ADD_ROLE_PERMISSION, params);
	}
	
	/**
	 * 给id为roleId的角色赋权限
	 * @param roleId 角色id
	 * @param permissionId 权限id
	 */
	public void addRolePermisson(long roleId, long permissionId){
		dbUtilsTemplate.update(ADD_ROLE_PERMISSION, new Object[]{roleId, permissionId, Role.State.STATE.getCode()});
	}
	
	/**
	 * 删除指定角色对应的所有权限
	 * @param roleId
	 */
	public void delRolePermisson(long roleId){
		dbUtilsTemplate.update(DEL_ROLE_PERMISSON, new Object[]{roleId});
	}
	/**
	 * 验证产品名称非重
	 *  @return total
	 */
	public long validatorRoleName(String id, String roleName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(0) FROM _roles WHERE 1=1 ");
		//if(!hk.linktech.flow.utils.StringUtils.isEmptyValue(proName)){
			sql.append(" and _roleName = ? ");
			if(id!=null){
				sql.append(" and _id <> ?");
				return dbUtilsTemplate.searchTotal(sql.toString(), roleName,id);
			}
		return dbUtilsTemplate.searchTotal(sql.toString(), roleName);
		//}
	}
	
}
