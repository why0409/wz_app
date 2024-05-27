package com.ruoyi.common.utils.sql;

import com.ruoyi.common.exception.UtilException;
import com.ruoyi.common.utils.StringUtils;

import java.util.List;

/**
 * sql操作工具类
 * 
 * @author ruoyi
 */
public class SqlUtil
{
    /**
     * 定义常用的 sql关键字
     */
    public static String SQL_REGEX = "select |insert |delete |update |drop |count |exec |chr |mid |master |truncate |char |and |declare ";

    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            throw new UtilException("参数不符合规范，不能进行查询");
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }

    /**
     * SQL关键字检查
     */
    public static void filterKeyword(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return;
        }
        String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
        for (String sqlKeyword : sqlKeywords)
        {
            if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1)
            {
                throw new UtilException("参数存在SQL注入风险");
            }
        }
    }
    /**
     * 动态拼接sql片段
     * @param list
     * @param param
     * @return
     * @throws Exception
     */
    public static String appendSql(List<? extends Object> list, String param) throws Exception {
        StringBuilder strWhere = new StringBuilder();
        if (list.size() > 0) {
            strWhere.append(" AND ( ").append(param).append("  in (");
        }
        for (int i = 0; i < list.size(); i++) {
            //大于1000个 处理方式是 拼接 or param in ()
            if (i < 999) {
                if (i == list.size() - 1 || i == 998) {
                    strWhere.append("'" + list.get(i) + "'");
                } else {
                    strWhere.append("'" + list.get(i) + "'" + ",");
                }
            } else {
                for (; i < list.size(); i++) {
                    if (i % 999 == 0) {
                        if (i == list.size() - 1) {
                            strWhere.append(" ) OR   ").append(param).append("    In ( '" + list.get(i) + "'");
                        } else {
                            strWhere.append(" ) OR   ").append(param).append("    In ( '" + list.get(i) + "',");
                        }
                    } else {
                        if ((i + 1) % 999 == 0 || i == list.size() - 1) {
                            strWhere.append(" '" + list.get(i) + "'");
                        } else {
                            strWhere.append("'" + list.get(i) + "',");
                        }
                    }
                }
            }
        }
        if (list.size() > 0) {
            strWhere.append(" ))");
        }
        return strWhere.toString();
    }
}
