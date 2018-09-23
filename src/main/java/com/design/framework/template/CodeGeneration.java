package com.design.framework.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.design.framework.utils.DateUtil;
import com.design.framework.utils.FileUtils;

/**
 * 代码生成器
 *
 * @author JohnDeng 2017年11月15日下午4:12:04
 */
public class CodeGeneration {
	
    private static final String tableName = "tm_user_info";
    private static final String entityName = "User";
    private static final String name = "用户";
    private static final String idType="String";

    protected Logger logger = Logger.getLogger(getClass());

    /**
     * 根据表名获取数据列
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> getColumnDate(String tableName, Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = null;
        try {
            pstm = connection.prepareStatement("SELECT * FROM " + tableName);
            rs = pstm.executeQuery();
            ResultSetMetaData rsme = rs.getMetaData();
            list = getDateListMap(rsme);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            } else if (pstm != null) {
                pstm.close();
            }
        }
        return list;
    }

    /**
     * 获取属性名称，类型，长度，是否为空
     *
     * @param data
     * @return
     */
    public List<Map<String, Object>> getDateListMap(ResultSetMetaData data) {
        List<Map<String, Object>> listDate = null;
        try {
            if (data != null && data.getColumnCount() > 0) {
                listDate = new ArrayList<Map<String, Object>>();
                Map<String, Object> map = null;
                int count = data.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String name = data.getColumnName(i);
                    if (name.equals("id") || name.equals("create_time")
                            || name.equals("update_time")
                            || name.equals("del_flag")
                            || name.equals("create_id")
                            || name.equals("update_id")) {
                        continue;
                    } else {
                        map = new HashMap<String, Object>();
                        map.put("name", data.getColumnName(i));
                        map.put("type", data.getColumnTypeName(i));
                        map.put("isNull", data.isNullable(i));
                        map.put("length", data.getPrecision(i));
                        
                        listDate.add(map);
                    }
                }
                return listDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDate;
    }

    /**
     * 创建set方法
     *
     * @param ColumnName
     * @return
     */
    public String getCreateSetFunction(String ColumnName, String type) {
        String set = null;
        if (ColumnName != null) {
            set = "    public void set" + formatName4(ColumnName) + "("
                    + mySqlTypeToJavaType(type) + " " + formatName5(ColumnName)
                    + ") {this." + formatName5(ColumnName) + " = "
                    + formatName5(ColumnName) + ";}\n";
            return set;
        }
        return set;
    }

    /**
     * 创建get方法
     *
     * @param ColumnName
     * @param type
     * @return
     */
    public String getCreateGetFunction(String ColumnName, String type) {
        String get = null;
        if (ColumnName != null && type != null) {
            get = "    public " + mySqlTypeToJavaType(type) + " get"
                    + formatName4(ColumnName) + "() { return "
                    + formatName5(ColumnName) + "; }\n";
            return get;
        }
        return get;
    }

    /**
     * 创建属性
     *
     * @param ColumnName
     * @param type
     * @return
     * @author John
     * @datatime 2017年10月27日下午3:34:23
     */
    public String getProperty(String ColumnName, String type) {
        String propertyStr = null;
        if (ColumnName != null && type != null) {
            propertyStr = "    private " + mySqlTypeToJavaType(type) + " "
                    + formatName5(ColumnName) + ";\n";
        }
        return propertyStr;
    }

    /**
     * 数据类类型转Java类型
     *
     * @param ColumnType 数据列类型
     * @return
     */
    public String mySqlTypeToJavaType(String ColumnType) {
        String type = null;
        if (ColumnType != null) {
            switch (TypeEnum.getByCode(ColumnType)) {
                case VARCHAR:
                    type = TypeEnum.VARCHAR.getType();
                    break;
                case CHAR:
                    type = TypeEnum.CHAR.getType();
                    break;
                case BLOB:
                    type = TypeEnum.BLOB.getType();
                    break;
                case TEXT:
                    type = TypeEnum.TEXT.getType();
                    break;
                case INTEGER:
                    type = TypeEnum.INTEGER.getType();
                    break;
                case TINYINT:
                    type = TypeEnum.TINYINT.getType();
                    break;
                case SMALLINT:
                    type = TypeEnum.SMALLINT.getType();
                    break;
                case MEDIUMINT:
                    type = TypeEnum.MEDIUMINT.getType();
                    break;
                case BIT:
                    type = TypeEnum.BIT.getType();
                    break;
                case BIGINT:
                    type = TypeEnum.BIGINT.getType();
                    break;
                case FLOAT:
                    type = TypeEnum.FLOAT.getType();
                    break;
                case DOUBLE:
                    type = TypeEnum.DOUBLE.getType();
                    break;
                case DECIMAL:
                    type = TypeEnum.DECIMAL.getType();
                    break;
                case BOOLEAN:
                    type = TypeEnum.BOOLEAN.getType();
                    break;
                case DATE:
                    type = TypeEnum.DATE.getType();
                    break;
                case DATETIME:
                    type = TypeEnum.DATETIME.getType();
                    break;
                case TIMESTAMP:
                    type = TypeEnum.TIMESTAMP.getType();
                    break;
                case INT:
                    type = TypeEnum.INT.getType();
                    break;
                default:
                    break;
            }
        }
        return type;
    }

    /**
     * 格式化name 例如：create_date->Create_date
     *
     * @param ColumnName
     * @return
     * @author John
     * @datatime 2017年10月27日下午4:08:38
     */
    public String formatName2(String ColumnName) {

        return ColumnName.substring(0, 1).toUpperCase()
                + ColumnName.substring(1, ColumnName.length());
    }

    /**
     * 格式化name user->User user_role->UserRole
     *
     * @param ColumnName
     * @return
     * @author John
     * @datatime 2017年10月30日下午3:14:06
     */
    public String formatName4(String ColumnName) {
        String arr[] = ColumnName.split("_");
        ColumnName = "";
        for (int i = 0; i < arr.length; i++) {
            ColumnName += arr[i].substring(0, 1).toUpperCase()
                    + arr[i].substring(1);
        }
        return ColumnName;
    }

    /**
     * 格式化name user->user user_role->userRole
     *
     * @param ColumnName
     * @return
     * @author John
     * @datatime 2017年10月31日上午11:08:15
     */
    public String formatName5(String ColumnName) {
        String arr[] = ColumnName.split("_");
        ColumnName = "";
        ColumnName += arr[0].substring(0, 1).toLowerCase()
                + arr[0].substring(1);
        for (int i = 1; i < arr.length; i++) {
            ColumnName += arr[i].substring(0, 1).toUpperCase()
                    + arr[i].substring(1);
        }
        return ColumnName;
    }

    /**
     * 拼接数据
     *
     * @param tableName  表名
     * @return
     * @throws SQLException
     */
    public String getEntityData(String tableName, Connection connection)
            throws SQLException {
        List<Map<String, Object>> listData = getColumnDate(tableName,
                connection);
        StringBuilder nameStr = new StringBuilder();
        StringBuilder typeStr = new StringBuilder();
        if (listData != null && listData.size() > 0) {
            for (Map<String, Object> entityMap : listData) {
                String name = entityMap.get("name").toString();
                String type = entityMap.get("type").toString();
                nameStr.append(getProperty(name, type));
                typeStr.append(getCreateSetFunction(name, type));
                typeStr.append(getCreateGetFunction(name, type));
            }
            nameStr.append(typeStr.toString());
        }
        return nameStr.toString();
    }

    /**
     * 获取mysql字段名
     *
     * @param columsListMap
     * @return
     * @author John
     * @datatime 2017年9月16日上午10:20:23
     */
    public List<String> getColumnsNameList(
            List<Map<String, Object>> columsListMap) {
        List<String> columnsNameList = new ArrayList<String>();
        for (Map<String, Object> columnsMap : columsListMap) {
            columnsNameList.add((String) columnsMap.get("name"));
        }
        return columnsNameList;
    }

    /**
     * 获取字段名拼接mysql
     *
     * @param columnList
     * @return
     * @author John
     * @datatime 2017年9月16日上午10:35:02
     */
    public String getColumns(List<String> columnList, String entityName) {
        StringBuilder columns = new StringBuilder();
        boolean flag = false;
        for (String name : columnList) {
            if (flag) {
                columns.append(",\n");
            }
            columns.append("		");
            columns.append(formatName5(entityName));
            columns.append(".");
            columns.append(name);
            columns.append(" AS ");
            columns.append(formatName5(name));
            flag = true;
        }
        return columns.toString();
    }

    /**
     * 获取新增的字段
     *
     * @param columnList
     * @return
     * @author John
     * @datatime 2017年9月17日上午12:28:22
     */
    public String getInsertColums(List<String> columnList) {
        StringBuilder insertColums = new StringBuilder();
        boolean flag = false;
        for (String name : columnList) {
            if (flag) {
                insertColums.append(",\n");
            }
            insertColums.append("			");
            insertColums.append(name);
            flag = true;
        }
        return insertColums.toString();

    }

    /**
     * 获取新增的值
     *
     * @param columnList
     * @return
     * @author John
     * @datatime 2017年9月17日上午12:35:07
     */
    public String getInsertValues(List<String> columnList) {
        StringBuilder insertValues = new StringBuilder();
        boolean flag = false;
        for (String name : columnList) {
            if (flag) {
                insertValues.append(",\n");
            }
            insertValues.append("			");
            insertValues.append("#{");
            insertValues.append(formatName5(name));
            insertValues.append("}");
            flag = true;
        }
        return insertValues.toString();
    }

    /**
     * <if test="updateTime != null">update_time=#{updateTime},</if>
     *
     * @param columnList
     * @return
     * @author John
     * @datatime 2017年11月20日上午9:21:26
     */
    public String getUpdateColumms(List<String> columnList) {
        StringBuilder updateValues = new StringBuilder();
        boolean flag = false;
        for (String name : columnList) {
            if (flag) {
                updateValues.append("\n");
            }
            updateValues.append("			");
            updateValues
                    .append("<if test=\"" + formatName5(name) + "!=null\">");
            updateValues.append(name);
            updateValues.append("=#{");
            updateValues.append(formatName5(name));
            updateValues.append("},</if>");
            flag = true;
        }
        return updateValues.toString();
    }

    public static void main(String[] args) throws SQLException,
            FileNotFoundException, IOException {
		
        Connection conn = DBConnection.getConnection();
        CodeGeneration c = new CodeGeneration();
        // //生成entity
        String entityString = c.getEntityData(tableName, conn);
        System.out.println(entityString);
        File entityFile = FileUtils.getFile(PathContext.TEST_FILE_ENTITY_PATH);
        System.out.println("读取实体模板文件成功");
        String fileString = IOUtils.toString(new FileInputStream(entityFile));
        fileString = fileString.replace("${entityName}", entityName);
        fileString = fileString.replace("${id}", idType);
        fileString = fileString.replace("${tableName}", tableName);
        fileString = fileString.replace("${property}", entityString);
        fileString = fileString.replace("${datatime}",DateUtil.date2Str(new Date()));
        fileString = fileString.replace("${name}", name);
        fileString = fileString.replace("${entityName1}",
                c.formatName5(entityName));
        FileUtils.wirteContent(PathContext.BASE
                        + PathContext.CREATE_ENTITY_PATH, entityName + ".java",
                fileString);
        System.out.println("生成实体模板文件成功");
        // 生成dao
        File daoFile = FileUtils.getFile(PathContext.TEST_FILE_DAO_PATH);
        System.out.println("读取dao模板文件成功");
        String daoFileString = IOUtils.toString(new FileInputStream(daoFile));
        daoFileString = daoFileString.replace("${entity}", entityName);
        daoFileString = daoFileString.replace("${id}", idType);
        FileUtils.wirteContent(PathContext.BASE + PathContext.CREATE_DAO_PATH,
                entityName + "Dao.java", daoFileString);
        System.out.println("生成dao模板文件成功");
        // 生成service
        File serviceFile = FileUtils
                .getFile(PathContext.TEST_FILE_SERVICE_PATH);
        System.out.println("读取service模板文件成功");
        String serviceString = IOUtils
                .toString(new FileInputStream(serviceFile));
        serviceString = serviceString.replace("${entity}", entityName);
        serviceString = serviceString.replace("${id}", idType);
        FileUtils.wirteContent(PathContext.BASE
                        + PathContext.CREATE_SERVICE_PATH, entityName + "Service.java",
                serviceString);
        System.out.println("生成service模板文件成功");
        // 生成serviceImpl
        File serviceImplFile = FileUtils
                .getFile(PathContext.TEST_FILE_SERVICE_IMPL_PATH);
        System.out.println("读取serviceImpl模板文件成功");
        String serviceImplFileString = IOUtils.toString(new FileInputStream(
                serviceImplFile));
        serviceImplFileString = serviceImplFileString.replace("${entity}",
                entityName);
        serviceImplFileString = serviceImplFileString.replace("${id}", idType);
        serviceImplFileString = serviceImplFileString.replace("${entity1}",
                c.formatName5(entityName));
        FileUtils.wirteContent(PathContext.BASE
                + PathContext.CREATE_SERVICE_IMPL_PATH, entityName
                + "ServiceImpl.java", serviceImplFileString);
        System.out.println("生成serviceImpl模板文件成功");
        // 生成controller
        File controllerFile = FileUtils
                .getFile(PathContext.TEST_FILE_CONTROLLER_PATH);
        System.out.println("读取controllerFile模板文件成功");
        String controllerFileString = IOUtils.toString(new FileInputStream(
                controllerFile));
        controllerFileString = controllerFileString.replace("${name}", name);
        controllerFileString = controllerFileString.replace("${entity}",
                entityName);
        controllerFileString = controllerFileString.replace("${entity1}",
                c.formatName5(entityName));
        controllerFileString = controllerFileString.replace("${id}", idType);
        controllerFileString = controllerFileString.replace("${datatime}",
                DateUtil.date2Str(new Date()));
        FileUtils.wirteContent(PathContext.BASE
                + PathContext.CREATE_CONTROLLER_PATH, entityName
                + "Controller.java", controllerFileString);
        // 生成dao.xml
        List<Map<String, Object>> columnsListMap = c.getColumnDate(tableName,
                conn);
        String columms = c.getColumns(c.getColumnsNameList(columnsListMap),
                entityName);
        String insertColumms = c.getInsertColums(c
                .getColumnsNameList(columnsListMap));
        String insertValues = c.getInsertValues(c
                .getColumnsNameList(columnsListMap));
        String updateColumms = c.getUpdateColumms(c
                .getColumnsNameList(columnsListMap));
        File file = FileUtils.getFile(PathContext.TEST_FILE_DAO_XML_PATH);
        System.out.println("读取dao.xml模板文件成功");
        String daoxmlString = IOUtils.toString(new FileInputStream(file));
        String dao = daoxmlString.replace("${entityName}", entityName);
        dao = dao.replace("${entityName1}", c.formatName5(entityName));
        dao = dao.replace("${tableName}", tableName);
        dao = dao.replace("${Columms}", columms);
        dao = dao.replace("${insertColumms}", insertColumms);
        dao = dao.replace("${insertValues}", insertValues);
        dao = dao.replace("${updateColumms}", updateColumms);
        FileUtils.wirteContent(PathContext.CREATE_DAO_XML_PATH, entityName
                + "Dao.xml", dao);
        System.out.println("读取dao.xml模板文件成功");
    }

}
