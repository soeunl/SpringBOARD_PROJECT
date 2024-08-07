/**
 * 파일 업로드, 삭제, 조회 공통 기능
 *
 */

const fileManager = {
    /**
     * 파일 업로드
     *
     */
    upload(file, gid, location) {
    // gid, location -> 관광 상품이나 상품 등록 시 메인 이미지, 상세 이미지 등으로 나눠서 넣을 때 활용 가능
        try {
            if (!files || files.length == 0) {
                throw new Error("파일을 선택 하세요.");
            }

            if (!gid || !gid.trim()) {
                throw new Error("필수 항목 누락 입니다(gid).");
            }

            const formData = new FormData();
            formData.append("gid", gid.trim());

            for(const file of files) {
                formData.append("file", file); // 앞에 있는 file이 전송되는 name값
                // 스프링에서도 이 name값으로 찾을 수 있다
            }

            if (location && location.trim()) {
                formData.append("location", location.trim());
            }

            const { ajaxLoad } = commonLib;

            ajaxLoad('/file/upload', 'POST', formData)
                .then(res => {
                    if (!res.success) {
                        alert(res.message);
                        return;
                    }
                    // 파일 업로드 후 처리는 다양, fileUploadCallback을 직접 상황에 맞게 정의 처리
                    if (typeof parent.fileUploadCallback === 'function') {
                        parent.fileUploadCallback(res.data);
                    }
                })
                .catch(err => alert(err.message));

        } catch (e) {
            console.error(e);
            alert(e.message);
        }
        // 콜백방식을 사용하는 이유는 상황에 따라 다르게 처리하기 위함이다..?
    },
    /**
     * 파일 삭제
     *
     */
    delete() {

    },
    /**
     * 파일 조회
     *
     */
    search() {

    }
};

window.addEventListener("DOMContentLoaded", function () {
    // 파일 업로드 버튼 이벤트 처리 S
    const fileUploads = document.getElementsByClassName("fileUploads");
    const fileEl = document.createElement("input");
    fileEl.type = 'file';
    fileEl.multiple = true;
    // 동적으로 요소 생성
    for(const el of fileUploads) {
        el.addEventListener("click", function () {
            fileEl.value = ""; // 클릭했을때는 새로 올리는 것이니깐 선택 값 초기화
            delete fileEl.gid;
            delete fileEl.location;
            const dataset = this.dataset; // dataset 이라는 속성을 이용해서 접근 가능하다. 추가, 제거, 삭제가 바로 반영이 가능하다
            fileEl.gid = dataset.gid;
            if(dataset.location) fileEl.location = dataset.location;

            fileEl.click(); // 파일 태그는 메모리에!
        });
    } // 파일 업로드 버튼 이벤트 처리 E

    // 파일 업로드 처리
    fileEl.addEventListener("change", function(e) {
        const files = e.target.files;
        fileManager.upload(files, fileEl.gid, fileEl.location);
    });
});