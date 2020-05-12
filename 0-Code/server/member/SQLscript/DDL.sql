-- 根据用户id查询他有什么权限

SELECT
    p.*
FROM
    user_role_relation ur
    LEFT JOIN role r ON ur.role_id = r.id
    LEFT JOIN role_permission_relation rp ON r.id = rp.role_id
    LEFT JOIN permission p ON rp.permission_id = p.id
WHERE
    ur.user_id = 1