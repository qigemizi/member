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

