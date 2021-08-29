## FakeKinopoiskApp

Android приложение для отображения списка фильмов и просмотра информации о них. 
<br>
За основу взято [тестовое задание](https://docs.google.com/document/d/1syJtP2kFDXX8XROuvDpk3jajWeRz_QkR7gho9eFD1Y4/edit?usp=sharing).

### Что из себя представляет:

  ##### 1 Fragment:
  * Группа Chips'ов, определяющая, фильмы каких жанров будут показываться в Recyclerview
  * Recyclerview, демонстрирующий список фильмов (зависящий от выбранного жанра) и по умолчанию имеющий сортировку по _localized_name_. При нажатии имитируется переход на второй фрагмент

  ##### 2 Fragment:
  * Подробная информация о выбранном фильме
  * Up button и label (локализованное имя) в панели


### Технологии, используемые в проекте:
* Kotlin
* MVVM Architecture
* Многопоточность: Kotlin Coroutines
* Architecture Components (Room, Data Binding, ViewModel, Livedata, Navigation)
* Recycler View
* Сетевые взаимодействия: Retrofit2
* Moshi
* Работа с изображениями: Glide

### Screenshots
![Screenshot](https://ibb.co/dPQ5y3X)

### Download


