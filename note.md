오버월드:
-64 until 320

네더월드:
0 until 256


# 땅 관련 규칙

* 내땅 / 상대땅 여부 없이 걍 다 진입 가능
* 소유자 없는 땅과 공용땅은 다름

---
소유자 없는 일반땅: 들어가도 효과 없
소유자 있는 일반땅: 그안에서 울팀은 무적 상대가 땅주인 못때림
(대신 블럭 설치하거나 뽀가는건 가능)

---
소유자 없는 효과땅: 아무나 들어가도 무적건 해당땅의 디버프 받음
소유자 있는 효과땅: 울팀이 들가면 버프받고 / 남팀이 들가면 디버프 받음

---
공용땅 (효과땅/일반땅 무관): 상태효과 없고 서로간 피빞 가능 / 특수땅 도끼 딸깍하면 바로 먹을수 있

---
뺏기는 중인 일반땅
(공용땅에서 1분동안 견디면 울땅되는 방식이 아니라 걍 뺏기는중인 땅을 따로 만듦):
해당 땅 안에서 다른팀간 피빞 가능 / 뺏는 팀의 팀원 1명이라도 지정된 시간동안 견디면 뺏어짐

뺏기는 중인 효과땅
(공용땅에서 1분동안 견디면 울땅되는 방식이 아니라 걍 뺏기는중인 땅을 따로 만듦):
해당 땅 안에서 다른팀간 피빞 가능 / 뺏는 팀의 팀원 1명이라도 지정된 시간동안 견디면 뺏어짐
뺏는팀에 대한 디버프가 무시되고 / 뺏기는 팀에겐 약한 버프가 걸림

> 뺏기는 중인 일반땅의 견디기 시간과 뺏기는 중인 효과땅의 견디는 중인 시간은 따로 설정 가능



# 코드상의 땅 종류:

---
`EMPTY_AREA`:
* `EmptyAreaState`
  * 위치, 크기
  * 땅 재생성 기능

> 재생성 기능만 있는 껍대기   
> 나중에 확률적으로 땅을 생성할때 그 기반이 되는땅



---
`GENERAL_AREA`:
* `GeneralAreaState`
  * 소유 여부, 소유자

* 해당하는 땅:
> 소유자 없는 일반땅   
> 소유자 있는 일반땅   

TODO

---
`EFFECT_AREA`:
* `EffectAreaState`
  * 소유 여부, 소유자
  * 효과 속성

* 해당하는 땅:
> 소유자 없는 효과땅   
> 소유자 있는 효과땅   

TODO

---
`PUBLIC_AREA`:
* `PublicAreaState`
  * 효과 속성
    >   효과 속성이 null 일경우 해당 땅은 공용 일반 땅이고,   
        효과 속성이 null 이 아닐경우 해당땅은 공용 효과 땅임   
        효과 속성이 있어도 적용되진 않음 (효과를 저장하기 위함)
        자세한건 땅 관련 규칙 참조   
  * 이전 팀 속성
    >   뺐긴 팀이 빼끼자 마자 공용땅 된곳에 도끼 쓸수도 있으니깐
    >   지정된 시간동안 도끼 쓰는거 막기 위함

* 해당하는 땅:
> 공용땅 (효과땅/일반땅 무관)

TODO

---
`WAR_GENERAL_AREA`:
* `WarGeneralAreaState`
  * 소유자
  * 뺏는쪽

* 해당하는 땅:
> 뺏기는 중인 일반땅

TODO

---
`WAR_EFFECT_AREA`:
* `WarEffectAreaState`
  * 소유자
  * 뺏는쪽

  * 효과 속성

* 해당하는 땅:
> 뺏기는 중인 효과땅

TODO



# 코드상에서 땅이 바뀌는 케이스들
* 땅의 타입이 바뀌는 모든 작업은 state 에 대해서 발생함

---
`EMPTY_AREA` -> `GENERAL_AREA`, `EFFECT_AREA`:
* 타입이 바뀌는 경우인
* 주인 없는 땅으로 바뀜

---
`GENERAL_AREA`, `EFFECT_AREA` -> 주인 있는 땅:
* state 의 속성이 바뀌는 경우인
* 주인 속성이 설정됨



---
주인 없는 `GENERAL_AREA`, `EFFECT_AREA` -> `WAR_AREA`:
* 안됨. `IllegalTypeChangeException` 터짐

---
주인 있는 `GENERAL_AREA`, `EFFECT_AREA` -> `WAR_AREA`:
* 타입이 바뀌는 경우인

* 기본적으로 땅이 뻈길때 발생하기 떄문에,
* `GeneralAreaState` 에 뺐기는 매서드가 따로 있고,
* 해당 매서드 내부에서 자신에 대해 `setType` 을 호출

* `WarAreaState` 에 기존 소유자 속성, 이펙트 속성과 
* 뺐는 팀이 새로 전달됨



---
`WAR_AREA` -> `PUBLIC_AREA`:
* 땅의 타입이 바뀌는 경우임
* `setType` 을 통해 발생함



---
`WAR_AREA` -> `GENERAL_AREA`, `EFFECT_AREA`:
* 안됨. `IllegalTypeChangeException` 터짐

---
`WAR_AREA` -> 원래 주인의 `GENERAL_AREA`, `EFFECT_AREA`:
* 땅의 타입이 바뀌는 경우인

* `gualdian` 이라는 개별 메서드을 통해 발생함
* 전쟁 지역에서 뻈는팀이 끝가지 견딜시 PUBLIC_AREA 가 되고,
* 거기에 일반 땅 도끼 써서 울팀 땅으로 만드는거임
* 원래 땅 소유자였던 팀이 PUBLIC_AREA

---
`WAR_AREA` -> 새 주인의 `GENERAL_AREA`, `EFFECT_AREA`:
* 안됨. `IllegalTypeChangeException` 터짐
* 전쟁 지역을 점령할떄는 한번 `PUBLIC_AREA` 를 거쳤다가
* 최종적으로 뻈는팀의 소유가 됨



---
주인 없는 `GENERAL_AREA`, `EFFECT_AREA` -> `PUBLIC_AREA`:
* 안됨. `IllegalTypeChangeException` 터짐

---
주인 있는 `GENERAL_AREA`, `EFFECT_AREA` -> `PUBLIC_AREA`:
* 안됨. `IllegalTypeChangeException` 터짐



# 코드가ㅏㅏㅏㅏㅏㅏㅏㅏㅏ
조만간 Area 객체들 다 고처ㅕ야 할듯
하나의 Area 를 다른 종류의 Area 로 바꾸는게
너무 불편하고 이레저레 문제가 많음

지금 바꾸는중