<template>
  <div>
    <h3>주문 상세 정보 관리</h3>
    <hr />

    <br />
    <br />

    <h3>주문 상세 조회 리스트</h3>
    <br />

    <div class="fw-bold fs-4 text-danger">주문 코드 : [주문코드]</div>

    <br />

    <h3>결제 상세 내역</h3>
    <hr />

    <table class="table">
      <thead>
        <tr class="table-secondary">
          <th scope="col">Email(ID)</th>
          <th scope="col">결제 방식</th>
          <th scope="col">결제 금액</th>
          <th scope="col">결제날짜</th>
          <th scope="col">취소날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>[Email(ID)]</td>
          <td>[결제 방식]</td>
          <td>[결제 금액]</td>
          <td>[결제날짜]</td>
          <td>[취소날짜]</td>
        </tr>
      </tbody>
    </table>

    <br />
    <br />

    <table class="table">
      <thead>
        <tr class="table-secondary">
          <th scope="col">제품 메인 이미지(Img)</th>
          <th scope="col">제품 코드</th>
          <th scope="col">제품명</th>
          <th scope="col">사이즈</th>
          <th scope="col">가격</th>
          <th scope="col">개수</th>
          <th scope="col">합산 가격</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>[제품(Img)]</td>
          <td>[제품 코드]</td>
          <td>[제품명]</td>
          <td>[사이즈]</td>
          <td>[가격]</td>
          <td>[개수]</td>
          <td>[합산 가격]</td>
        </tr>
      </tbody>
    </table>

    <br />
    <br />

    <h3>수취인/배송 정보</h3>
    <hr />

    <table class="table">
      <thead>
        <tr class="table-secondary">
          <th scope="col">배송코드</th>
          <th scope="col">수취인 이름</th>
          <th scope="col">수취인 전화번호</th>
          <th scope="col">수취인 주소</th>
          <th scope="col">택배사</th>
          <th scope="col">배송상태</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>[배송코드]</td>
          <td>[수취인 이름]</td>
          <td>[수취인 전화번호]</td>
          <td>[수취인 주소]</td>
          <td>[택배사]</td>
          <td>[배송상태]</td>
        </tr>
      </tbody>
    </table>

    <br />
    <br />

    <div class="text-center">
      <a href="/AdminOrder"
        ><button type="button" class="btn btn-danger me-2">삭제</button></a
      >

      <a href="/admin/AdminOrder"
        ><button type="button" class="btn btn-secondary">
          확인/뒤로가기
        </button></a
      >
    </div>

    <br />
    <br />
  </div>
</template>
<script>
export default {};
</script>
<style></style>
