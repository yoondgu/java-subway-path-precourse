# [미션 - 지하철 노선도 경로 조회] 명세서

## 목차

1. [기능 목록](#-기능-목록)
2. [상수값 목록](#-상수값-목록)
3. [단위 테스트 목록](#-단위테스트-목록)
4. [클래스 설계](#-클래스-설계)
5. [리팩토링 검토 목록](#%EF%B8%8F-리팩토링-검토-목록)

## 🚀 기능 목록

### 로직 분리에 따른 예외 발생 기준

- 예외 발생 시 적합한 표준예외를 사용한다.
- 기본 UI 검증 오류는 view에서, 도메인 기능에서 발생하는 오류는 service에서 예외를 발생시킨다.

### 도메인 구성 요소

- 지하철역
  - 이름
- 지하철 노선
  - 이름
- 노선 경로 (구간의 모음)
  - 노선 이름
  - 모든 구간의 정보 (순서대로)
- 구간
  - 해당 구간을 이루는 두 역
  - 구간의 거리
  - 소요 시간

- 조회 경로
  - 1개 이상의 구간의 모음
  - 거리
  - 소요 시간

- 지하철역 목록
- 지하철 노선 목록
- 노선 경로 목록

### 도메인 로직을 위한 기능

1. 
    - [ ] 모든 노선에 포함된 역 정보를 중복 없이 조회하는 기능
2. 
    - [ ] 모든 노선에 포함된 모든 구간 정보를 조회하는 기능
3. 
    - [ ] (최단 거리 경로 조회) 출발역, 도착역을 전달받아 거리 기준 최단 경로를 조회하는 기능 
      - 1, 2번 정보를 이용한다.
      - [ ] `예외발생` : 출발역이 존재하지 않는 경우
      - [ ] `예외발생` : 도착역이 존재하지 않는 경우
      - [ ] `예외발생` : 출발역과 도착역이 동일한 경우
      - [ ] `예외발생` : 출발역과 도착역이 연결되어 있지 않은 경우 (두 역이 서로 다른 노선에 있고, 교점이 하나도 없을 때)
4. 
    - [ ] (최소 시간 경로 조회) 2, 3번으로 조회한 역 정보, 구간 정보를 이용해 출발역 ~ 도착역의 시간 기준 최단 경로를 조회하는 기능 
      - 1, 2번 정보를 이용한다.
      - [ ] `예외발생` : 출발역이 존재하지 않는 경우
      - [ ] `예외발생` : 도착역이 존재하지 않는 경우
      - [ ] `예외발생` : 출발역과 도착역이 동일한 경우
      - [ ] `예외발생` : 출발역과 도착역이 연결되어 있지 않은 경우 (두 역이 서로 다른 노선에 있고, 교점이 하나도 없을 때)
5. 
    - [ ] 조회 경로를 전달받아 해당 경로의 총 거리, 소요시간을 계산하는 기능
      - [ ] `예외발생` : 구간이 연결되어 있지 않은 경우

### UI 로직을 위한 기능

1. 
    - [ ] 메인 화면 출력
2. 
    - [ ] 메뉴 출력
3. 
    - [ ] 세부 기능 화면 출력
4. 
    - [ ] 입력 안내 메시지 출력
5. 
    - [ ] 역 이름 입력
6. 
    - [ ] 기능 번호 입력
7. 
    - [ ] 오류 메시지 출력
8. 
    - [ ] 조회 결과 출력

## 🗄 상수값 목록

### 도메인 로직 정보

- [ ]

### UI 로직 정보

- [ ]

## ✅ 단위테스트 목록

### 도메인 로직 단위테스트

- [ ] 기능1 단위테스트
    - [ ] 

## 🖋 클래스 설계

[작성 예정]

## ♻️ 리팩토링 검토 목록

### 코드 스타일 및 요구사항

- [ ] 인덴트 2 이하 검토
- [ ] 메소드 분리 검토
- [ ] 메소드 길이 10라인 이하 검토
- [ ] 파라미터 개수 3개 이하 검토
- [ ] 변수, 메소드 선언 순서 정리 (메소드 정렬 기준 1순위: 메소드 간 논리적 연관성, 2순위: public-private)
- [ ] Java 코드 컨벤션 가이드 준수 검토
- [ ] 요구사항에 주어진 클래스 적용 규칙 검토
- [ ] TODO 주석 모두 해결 후 삭제
- [ ] 클래스 주석 작성