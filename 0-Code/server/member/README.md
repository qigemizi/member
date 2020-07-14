# member
会员管理系统

本系统user就代表admin，即用户就是管理员
完成情况：
1、在需要权限的接口上使用@PreAuthorize注解定义好需要的权限。已完成
    @ResponseBody
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('member:list:read')")
    public CommonResult memberList(){
        System.out.println("进入memberList方法了");
        List<Member> memberList = memberService.list();
        return CommonResult.success(memberList);
    }
这样需要在每个接口上都定义好访问该接口的权限值

2、打算写动态权限配置


