# code-interview-backend

지원자 분의 개발 역량과 방향을 알아보기 위한 사전 과제입니다.

과제 전형 통과 이후, 실무 인터뷰 과정에서 해당 코드에 관한 리뷰를 진행할 수 있습니다.



**개발 환경**
- 
- Spring boot 3.x.x
- Java 17 
- JPA QueryDSL 외 자유



**과제 사전 가이드**
-
- 메일로 전달드린 기한까지 main branch에 반영 부탁드립니다.
- 제출 기한이 지난 시점부터 작성된 항목은 평가에서 제외됩니다.
- 제시된 과제 조건 이외의 추가 기능 또는 설계는 자유입니다.
- 과제에 이상이 있는 경우, 해당 레포지토리의 Issue탭에 Question 라벨로 문의 부탁드립니다.
- 수행한 과제에 대한 개별적인 상세 피드백은 어려울 수 있다는 점 말씀드립니다.

 



**설명**
- 
- 여러 서비스가 Lifelog 플랫폼을 이용하는 형태 ( 1:N )
- userId는 서비스 별로 중복되지 않는 형태를 가짐
- 라이프로그 항목은 기재된 항목 이외에도 N종이 추가될 예정
- 유저의 혈압 측정 값은 개인정보보호 항목으로 암호화 필요
- 조회시 복호화 된 값을 표출


- (선택사항) 동기 API 통신 또는 비동기 이벤트(MQ)로 처리하는 방식도 가능함. 편한 방법으로 구현

** **

**✅ 유저의 라이프로그 혈압 저장 API**
  
```[POST] v1/users/{userId}/blood-pressure```
- 혈압 json의 내용을 모두 DB 내에 저장하는 기능
- 변수명과 형태, 추가적인 데이터는 자유롭게 개발 가능
- 유저는 하루에 여러 번 혈압을 입력할 수 있음

**✅ 유저의 라이프로그 혈압 목록 조회 API**
  
```[GET] v1/users/{userId}/blood-pressure```
- 저장한 혈압 로그를 유저별로 조회하는 기능
- 유저당 N개의 혈압 로그가 조회되어야 함

**✅ 유저의 라이프로그 혈압 통계 API**
  
```[GET] v1/users/{userId}/blood-pressure/timeline```
- 저장된 혈압 로그를 월간/주간 기준으로 통계를 추출하는 기능
- 각 일자 별로 최저/최고 수축기/이완기 혈압을 나타냄 (사진 참조 - 삼성헬스)

<img width="239" alt="image" src="https://github.com/user-attachments/assets/02502f45-6cd5-4b37-b197-de9edd5ba791" />



라이프로그 종류
- 
- 혈압
- 걸음
- 체중



**혈압  json**
```json
{
  "logType": "BLOOD_PRESSURE",
  "createTime": "2025-03-25T12:34:56.789Z",
  "updateTime": "2025-03-25T12:45:00.123Z",
  "startTime": "2025-03-25T12:30:00.000Z",
  "isActive": true,
  "systolic": 120.5,
  "diastolic": 80.3
}
```

**걸음 수 json** (예시용)
```json
{
  "logType": "STEP_COUNT",
  "createTime": "2025-03-25T12:34:56.789Z",
  "updateTime": "2025-03-25T12:45:00.123Z",
  "startTime": "2025-03-25T12:30:00.000Z",
  "isActive": true,
  "stepCount": 10500,
  "calorie": 320.5
}
```

**체중 json** (예시용)
```json
{
  "logType": "WEIGHT",
  "createTime": "2025-03-25T12:34:56.789Z",
  "updateTime": "2025-03-25T12:45:00.123Z",
  "startTime": "2025-03-25T12:30:00.000Z",
  "isActive": true,
  "weight": 68.5,
  "height": 172.3
}
```
