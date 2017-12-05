# FlipCardView
카드를 뒤집듯이 볼 수 있는 뷰가 필요해서 만들었다.

# 스크린샷
![Screenshot](https://github.com/karrel84/FlipCardView/blob/master/screenshot.png?raw=true)

# JitPack에서 그래들로 받기
* root build.gradle에 jitpack을 추가합니다.
```java
 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
* app build.gradle의 dependencies에 라이브러리 주소를 추가합니다.
```java
	dependencies {
	        compile 'com.github.karrel84:FlipCardView:1.0'
	}
```

# 사용방법
* xml에 FlipCardView를 추가
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white"
        tools:context="flipview.com.karrel.flipviewsample.view.MainActivity">


        <flipview.com.karrel.flipcardview.view.FlipCardView
            android:id="@+id/flipCard"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />


    </android.support.constraint.ConstraintLayout>

</layout>
```
* flipCardView 에 카드 A와 B넣기
```java
    private void setupCardView() {
        ViewCardABinding aBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_card_a, null, false);
        binding.flipCard.setCardA(aBinding.getRoot());


        ViewCardBBinding bBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_card_b, null, false);
        binding.flipCard.setCardB(bBinding.getRoot());
    }

```


# 라이센스
 ```code
Copyright 2017 Karrel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
