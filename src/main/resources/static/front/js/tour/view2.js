const items = [
    [126.94062742683245, 37.557756188912954],
    [126.94120499658828, 37.557287959390024],
    [126.94069261563956, 37.561184514897825]
];

window.addEventListener("DOMContentLoaded", function() {
    const mapEl = document.getElementById("map");
    mapEl.style.width = "1000px";
    mapEl.style.height = "600px";

    // 여러 개의 마커 객체

    const map = new kakao.maps.Map(mapEl, {
        center: new kakao.maps.LatLng(items[0][1], items[0][0]),
        level: 3,
    });

    const markers = items.map(pos => {
        const position = new kakao.maps.LatLng(pos[1], pos[0]);
        return new kakao.maps.Marker({position});
    });

    markers.forEach(marker => marker.setMap(map));
    
    const iwContent = '<h1>정보</h1>'; 
    // 마커보다 조금 더 위로 올라가거나 위치를 잡아야 한다
    const iwPos = new kakao.maps.LatLng(items[0][1] + 0.02, items[0][0]);

    const infoWindow = new kakao.maps.InfoWindow({
        position: iwPos,
        content: iwContent
    });

    infoWindow.open(map, markers[0]);

    // 마커 지우기
     const removeEls = document.getElementsByClassName("remove");
     for (let i = 0; i < removeEls.length; i++) {
         removeEls[i].addEventListener("click", function () {
             markers[i] .setMap(null); // 마커 지우기
         });
     }

/*
    let map;

    navigator.geolocation.getCurrentPosition((pos) => {
        const { latitude, longitude } = pos.coords;

        const mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3,
        };

        map = new kakao.maps.Map(mapEl, mapOption);

        const markerPos = new kakao.maps.LatLng(latitude, longitude);
        const marker = new kakao.maps.Marker({
            position: markerPos
        });

        marker.setMap(map); // 새로운 마커 객체를 만들어서 노출
        mapProcess(map);
    });

    function mapProcess(map) {
        // 지도 클릭시 좌표 정보
        kakao.maps.event.addListener(map, 'click', function(e) {
            const latLng = e.latLng;
            const marker = new kakao.maps.Marker({
                position: latLng
            });
            marker.setMap(map); // 마커 객체가 만들어진다.
            // 마커 객체를 제거하기 위해서는 null값을 넣어주면 된다.
        });
    }
    */
});