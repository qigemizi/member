import axios from "axios";

export function getMember(id) {
    // 不return出去，会报.then未定义
    return axios({
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        method: "GET",
        url: "/member/" + id
    });
}
export function updateMember(id, data) {
    return axios({
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        method: "POST",
        url: "/member/updateMember/" + id ,
        data: data,
    });
}
export function createMember(data) {
    console.log("data=");
    console.log(JSON.stringify(data));
    return axios({
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        method: "POST",
        url: "/member/createMember" ,
        data: JSON.stringify(data),
    });
}

