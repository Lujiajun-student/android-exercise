# 1 Android 新项目开发

首先需要下载Android Studio。

然后创建项目时使用Empty Views Activity。

在配置项目时，需要选择Java语言，然后最低的SDK选择API28，能够兼容安卓9.0即可。

![image-20260306221900587](./README_Picture/image-20260306221900587.png)

创建完毕后，就能看到一个基础项目。

![image-20260306222108026](./README_Picture/image-20260306222108026.png)

## 1.1 基础项目

首先，app目录是主要的工作区。

`src/main/java`存放Java或Kotlin代码。`com.example.myapplication`是包名，决定了应用在应用商店的唯一性。

`src/main/res`用于存放非代码资源。layout存放xml布局文件，drawable存放图片，values存放颜色、字符串、样式等。`AndroidManifest.xml`记录用户的名称、图标、界面和需要的权限。

`Gradle`定义了应用编译版本、第三方库等。

```java
package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// MainActivity 继承AppCompatActivity，能够适配不同版本的Android
public class MainActivity extends AppCompatActivity {

    // onCreate Activity的生命周期方法，页面被创建时自动调用onCreate方法。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 通过super运行父类的初始化代码
        super.onCreate(savedInstanceState);
        // 让应用内容充满整个屏幕
        EdgeToEdge.enable(this);
        // 将当前的Java代码与activity_main的xml布局绑定起来
        setContentView(R.layout.activity_main);
        // 使用监听器处理全屏适配，确保按钮或蚊子不会被手机刘海或下方虚拟按键挡住
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // 获取当前状态栏和导航栏占用空间，Insets对象保存了上下左右四个方向的占用像素值
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // 将获取到的像素值设置为当前页面的内边距，避免页面被遮挡
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            // 将获取到的占用空间信息进行返回，使得其他View也能收到这个通知
            return insets;
        });
    }
}
```

首先，软件内的安卓模拟器需要开启开发者选项，打开USB debugging才能在软件进行调试。

![image-20260306231409469](./README_Picture/image-20260306231409469.png)

## 1.2 开发语言

App开发有原生开发与混合开发两种技术路线，编程语言为Java和Kotlin。

## 1.3 工作目录结构

项目主要有两个模块：app和Gradle Scripts。

app下有三个目录：，manifests, java和res。

而Gradle Scripts主要是工程的编译配置文件。

![image-20260306232615206](./README_Picture/image-20260306232615206.png)

### 1.3.1 manifests

manifests是清单文件，描述了应用的包名、权限、声明的四大组件，以及应用的图标和主题。

### 1.3.2 kotlin+java

这里存放业务逻辑代码，可以使用Kotlin或Java。

`com.example.myapplication`是包名，作为应用商店的唯一标识。而`MainActivity`是应用的入口，负责显式UI和用户交互。

`com.example.myapplication (androidTest)`负责仪器化测试，测试必须运行在Android设备上，用来测试UI交互或功能。

`com.example.myapplication (test)`是本地单元测试，运行在本地JVM上，不依赖Android环境。

### 1.3.3 java

`java (generated)`是生成工具在编译期间生成的代码，不需要手动修改。

### 1.3.4 res

`res`是资源文件。安卓采用UI与逻辑分离的思想，由res存放资源文件。`drawable`存放位图或矢量图，`layout`存放XML格式的界面布局文件，`mipmap`存放应用的启动图标，`values`存放字符串、颜色、主题等。`xml`存放其他辅助配置。

### 1.3.5 Gradle Scripts

这是自动化构建系统，安卓项目的构建工具，负责将代码和资源编译成APK或AAB安装包。

`build.gradle.kts (Project: My_Application)`是项目级构建脚本，用来配置整个项目所有模块的插件和全局属性。

`build.gradle.kts (Module: app)`模块级构建脚本。在这里配置DK版本、应用版本号以及第三方库。

`proguard-rules.pro`用来混淆与压缩配置。发布Release版本时，能够使用ProGuard工具对代码进行混淆，防止被反编译。

`gradle.properties`是全局属性配置，如配置编译时的内存大小。

`gradle-wrapper.properties`是Gradle包装器配置。指定了项目的Gradle软件版本。

`libs.versions.toml`版本目录。将所有依赖库的版本号进行集中管理。

`local.properties`本地环境配置。配置本地电脑的Android SDK路径。

`settings.gradle.kts`项目设置。定义了目录包含哪些模块。

### 1.3.6 重要文件AndroidManifest.xml

在项目的manifests目录中，自动创建了`AndroidManifest.xml`文件。

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyApplication">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

* allowBackup：是否允许应用备份。通过备份，能够在用户数据丢失时进行数据恢复。
* icon：指定App在手机屏幕上显示的图标。
* label：指定App在手机屏幕显示的名称。
* roundIcon：指定App的圆角图标。
* supportsRtl：设置是否支持阿拉伯语、波斯语等从右往左的文字排列顺序。
* theme：指定App的显示风格。

还有，其中的activity标签是活动页面的注册，只有在activity标签中设置了活动页面，才能在手机应用中打开该页面。而配置`MAIN`和`LAUNCHER`，用处就是打开页面后，第一个展示的activity是什么。

## 1.4 页面显示和逻辑处理

Android实际上就是通过XML描绘应用界面，使用Java代码来书写程序逻辑。

这样能够将App的界面设计和代码逻辑分开。

现在尝试在layout中的`activity_main.xml`中编写程序的页面。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="hello World!"
        />

</LinearLayout>
```

这里在页面中展示了最基础的Hello World文字。

![image-20260307211054544](./README_Picture/image-20260307211054544.png)

接下来要进行介绍。

1. 声明部分。第一行的`?xml`标签的作用是指定XML的版本以及字符编码。
2. 根布局标签。也就是`LinearLayout`。这是视图组，决定了内部子视图的排列顺序。

* `xmlns:android`：命名空间。指定android库，其他属性以`android:`开头，用来表示这些属性使用的是Android标准库的属性。
* `android:layout_width`和`android:layout_height`，设置当前页面的宽高。使用`match_parent`表示该布局占据父容器的全部空间。
* `android:orientation`用来决定子视图的排列方向，选择`vertical`表示子组件从上到下一次排列。
* `android:gravity`。重力属性，决定所有子视图相对父布局的对齐方式。center让内部标签在屏幕上水平和垂直方向都居中显示。

3. 子控件标签。TextView是文本视图，用于在屏幕上渲染文本。

* `android:id`是资源标识符。`@+id/`表示在`R.java`文件中创建新的ID条目，这样在Java代码中通过`findViewById(r.id.tv)`来找到这个控件。
* `android:layout_width`和`android:layout_height`用于控制控件的大小。`wrap_content`表示控件大小根据内容自适应包裹，恰好显示文字即可。
* `android:text="hello world"`定义了该控件的文本内容。**这里的字符串应进行封装，放到res下的value文件即可。**

那么，接下来能够将主函数`MainActivity`进行修改，在onCreate函数中展示当前已经改好的页面。

```java
package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity 继承AppCompatActivity，能够适配不同版本的Android
public class MainActivity extends AppCompatActivity {

    // onCreate Activity的生命周期方法，页面被创建时自动调用onCreate方法。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 通过super运行父类的初始化代码
        super.onCreate(savedInstanceState);
        // 在页面加载布局文件activity_main.xml
        setContentView(R.layout.activity_main);
        // 从布局文件中找到id为tv的TextView控件
        TextView tv = findViewById(R.id.tv);
        // 修改控件的文本内容
        tv.setText("你好，世界");
    }
}
```

这样，就实现了自己创建页面，并在主函数中展示页面和修改页面内容。

## 1.5 Activity跳转

创建App页面有三个步骤。

1. 在layout目录中创建xml文件。
2. 创建与xml文件对应的Java代码。
3. 在`AndroidManifest.xml`中注册页面。

### 1.5.1 创建xml文件

首先，在layout中创建xml页面时，能够直接指定创建的xml文件类型，选择layout类型即可。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/text"/>

</LinearLayout>
```

其中，指定字符串最好的方法是`@string/path`。这会自动在values下的string文件中寻找对应name为text的标签内容作为最终的字符串。

```xml
<resources>
    <string name="app_name">My Application</string>
    <string name="hello_world">Hello World!</string>
    <string name="text">你好，世界</string>
</resources>
```

### 1.5.2 构造MainActicity2的Java文件

```java
package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActicity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 展示新的页面
        setContentView(R.layout.activity_main2);
    }
}
```

这样，在新的文件中通过`setContentView`来展示新的页面。

### 1.5.3 注册页面

最后一步就是在`AndroidManifest.xml`中注册这个新的页面。

![image-20260307214307875](image-20260307214307875.png)

由于新添加的页面不是主页面，因此不需要配置`intent-filter`属性。

接下来需要在主页面中添加按钮，实现点击跳转的功能。

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        />
    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/jump"/>

</LinearLayout>
```

这样在主页面上添加了按钮后，在`MainActivity`中为这个按钮添加跳转事件。

```java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity 继承AppCompatActivity，能够适配不同版本的Android
public class MainActivity extends AppCompatActivity {

    // onCreate Activity的生命周期方法，页面被创建时自动调用onCreate方法。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 通过super运行父类的初始化代码
        super.onCreate(savedInstanceState);
        // 在页面加载布局文件activity_main.xml
        setContentView(R.layout.activity_main);
        // 从布局文件中找到id为tv的TextView控件
        TextView tv = findViewById(R.id.tv);
        // 修改控件的文本内容
        tv.setText("你好，世界");

        // 实现点击跳转
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActicity2.class);
                startActivity(intent);
            }
        });
    }
}
```

首先，要在这里通过`findViewById`来寻找到对应id的按钮。找到按钮后，通过`setOnClickListener`来为这个按钮添加点击监听器，如果按钮被点击，就会执行里面定义的函数。而里面定义的函数是`View.OnClickListener(){}`，这是匿名内部类，在里面重写onClick方法，那么点击按钮时就会运行onClick。

为了实现跳转，需要在onClick方法里定义意图实例。意图中，需要通过setClass来定义跳转的起始点和重点，通过`MainActivity.this`来获取当前位置，再写其他页面对应的Java类，然后通过`startActivity(intent)`就能实现跳转的功能。

# 2. Android 页面

## 2.1 简单控件

简单控件指最基础的组件，用于显示文本、接收输入等。简单控件定义在xml布局文件中，在Activity中进行逻辑处理。为了展示控件的使用，需要在原项目添加模块chapter03。

### 2.1.1 文本显示

设置文本有两种方式。

1. 通过属性`android: text`来读取文本。

在layout下创建`activity_text_view.xml`文件，根标签为`LinearLayout`。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv_hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/say_hello"
        />

</LinearLayout>
```

这样，一个基础页面就完成了。

其中，这里通过`android:text`来读取字符串，字符串需要写在strings文件中。

```xml
<resources>
    <string name="app_name">chapter03</string>
    <string name="say_hello">Hello World!</string>
</resources>
```

接下来在kotlin+java包下创建Activity文件。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置页面文件
        setContentView(R.layout.activity_text_view);
        // 获取文本控件
        TextView tv = findViewById(R.id.tv_hello);
    }
}
```

这样就实现了文本显示功能。

2. Java代码中获取文本标签，通过`setText`来设置文本。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置页面文件
        setContentView(R.layout.activity_text_view);
        // 获取文本控件
        TextView tv = findViewById(R.id.tv_hello);
        // 通过setText设置文本内容
        tv.setText("你好，世界");
    }
}
```

### 2.1.2 文本大小

文本大小也有两种设置的方法。

第一种是通过`android:testSize`来给定。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv_hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/say_hello"
        android:textSize="50sp"
        />

</LinearLayout>
```

或者在Java文件中设置。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置页面文件
        setContentView(R.layout.activity_text_view);
        // 获取文本控件
        TextView tv = findViewById(R.id.tv_hello);
        // 通过setText设置文本内容
        tv.setText("你好，世界");
        // 设置文本大小
        tv.setTextSize(50);
    }
}
```

### 2.1.3 文本颜色

文本颜色也有两种。

第一种是在xml页面设置`android:textColor`。

第二种是在Java上设置`setTextColor`。

而颜色通常是在values文件夹里的colors文件中指定，这样能够在其他文件里调用。

还有背景颜色能够通过`setBackgroundColor`来进行设置。

### 2.1.4 视图宽高

视图是指屏幕上一个可交互的矩形区域。而视图宽高可以有三种方式进行设置。

1. match_parent。与上级视图保持一致。如果没有上一级，就填满整个屏幕。
2. wrap_content。与内容自适应。
3. dp。通过指定dp来控制尺寸。

### 2.1.5 视图间距

视图间距是指视图与视图之间、视图与边界之间的空间距离。主要有外边距margin和内边距padding。外边距是指当前视图与其他视图之间的距离。内边距是指视图内容和当前视图的边框之间的距离。

### 2.1.6 对齐方式

视图有两种对齐方式。

1. layout_gravity属性。指定了当前视图相对于上级视图的对齐方式。

如果要指定当前视图在父容器的顶端、下方等位置，使用`android:layout_gravity="top"`等即可。

2. gravity属性，指定了下级视图相对于当前视图的对齐方式。

![image-20260308225144419](./README_Picture/image-20260308225144419.png)

上面第一个LinearLayout指定了`layout_gravity`属性，就使得该视图处于父容器的底部。

而此时可以看到，该视图内的子视图默认处于左上角。而使用`gravity`属性设置为bottom，就会让子视图处于当前视图的底部。

![image-20260308225401452](./README_Picture/image-20260308225401452.png)

### 2.1.7 常用布局

#### 2.1.7.1 线性布局LinearLayout

线性布局是指按照一个方向来排列子视图，而这个方向只能说水平或者垂直，通过`android:orientation`来指定。

也就是说，如果在线性视图中定义了两个按钮，水平线性布局会导致两个按钮一左一右在视图的左上角排列。而垂直线性布局会使两个按钮一上一下在视图的左上角排列。

水平布局中还有一个属性`layout_weight`。作用是指定权重。在视图中如果有多个视图，希望这些视图按照比例来分配空间，就可以使用这个权重来自动分配，而相关视图的宽或者高需要设置为0dp。

比如两个视图的宽均设置为0dp，而`layout_weight`分嗯别设置为1和2，那么第一个视图的宽会占据父视图的1/3，第二个视图会占据2/3。而这个权重属性会自动识别宽或高。

#### 2.1.7.2 相对布局

相对布局RelativeLayout是根据其他View或父布局的位置关系来摆放控件的布局容器。

在相对布局中给定两个子视图bt1和bt2，那么可以通过`android:layout_toRightOf="@id/btn1"`来设置视图bt2位于bt1的右方。这样设置参照物来指定视图位置。

![image-20260308231351820](./README_Picture/image-20260308231351820.png)

这里设置了一个子视图，如果没有设置其他属性，那么当前视图会默认在左上角。如果要显示在父视图的中间，可以使用`layout_centerInParent="true"`，这样就是让当前视图位于父容器的中间。

![image-20260308231508336](./README_Picture/image-20260308231508336.png)

#### 2.1.7.3 网格布局

网格布局是将当前的视图按行数、列数进行划分。通过columnCount指定列数，也就是每行能够存放多少个视图，rowCount指定行数，也就是每列存放多少个视图。

![image-20260308232933056](./README_Picture/image-20260308232933056.png)

在这里的视图设置了columnCount和rowCount均为2，那么当前视图就会被分为4块，每一块分别从左到右、从上到下来分配给子视图使用。

而由于子视图手动设置宽高，导致父视图未填满页面，因此可以让子视图使用权重来分配空间。

![image-20260308233449728](./README_Picture/image-20260308233449728.png)

同时添加gravity属性，让子视图内子视图居中，也就是让文本居中。

### 2.1.8 滚动视图

滚动视图用于屏幕滚动。主要由两种：

1. 垂直滚动`ScrollView`。垂直滚动时，`layout_width`设置为`match_parent`，`layout_height`设置为`wrap_content`。
2. 水平滚动`HorizontalScrollView`。`layout_width`设置为`wrap_content`，`layout_height`设置为`match_parent`。 

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <HorizontalScrollView
        android:layout_width="wrap_content"
        android:layout_height="200dp">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <View
                android:layout_width="300dp"
                android:layout_height="match_parent"
                android:background="#aaffff" />

            <View
                android:layout_width="300dp"
                android:layout_height="match_parent"
                android:background="#ffff00"/>

        </LinearLayout>

    </HorizontalScrollView>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <View
                android:layout_width="match_parent"
                android:layout_height="400dp"
                android:background="#00ff00" />

            <View
                android:layout_width="match_parent"
                android:layout_height="400dp"
                android:background="#ffffaa"/>

        </LinearLayout>

    </ScrollView>

</LinearLayout>
```

这个页面存在两个视图，宽度分别为300dp，已经超出视图。那么只要使用`HorizontalScrollView`包裹，就能成功实现水平方向的滚动。而垂直滚动也是一样，通过`ScrollView`并使用垂直的LinearLayout，就能给出两个VIew，实现垂直滚动。

### 2.1.9 按钮

按钮Button继承了TextView，因此可以按照HTML的形式来使用Button。

Button拥有默认的按钮背景，而TextView没有背景；

Button内部会居中对齐，而TextView默认靠左对齐。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="下面的按钮英文默认大写"
        android:textColor="@color/black"
        android:textSize="17sp"/>
    
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textColor="@color/black"
        android:textSize="17sp"/>
    
</LinearLayout>
```

![image-20260312234419599](./README_Picture/image-20260312234419599.png)

同样的，按钮可以使用onClick来配置事件。但现在不推荐，使用`setOnClickListener`更合适。

按钮拥有点击事件和长按事件。

点击事件通过`setOnCLickListener`来设置，长按事件通过`setOnLongClickListener`来设置。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="下面的按钮英文默认大写"
        android:textColor="@color/black"
        android:textSize="17sp"/>

    <Button
        android:id="@+id/btn_single"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="单独的点击监听器"
        android:textColor="@color/black"
        android:textSize="17sp"/>
    
    <TextView
        android:id="@+id/tv_result"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="5dp"
        android:gravity="center"
        android:textColor="#000000"
        android:textSize="15sp"
        android:text="按钮点击结果"/>

</LinearLayout>
```

首先如果要设置监听器，需要为按钮添加id，方便使用Java获取标签。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        Button btn = findViewById(R.id.btn_single);
        TextView tv_result = findViewById(R.id.tv_result);
        btn.setOnClickListener(new MyOnClickListener(tv_result));
    }

    static class MyOnClickListener implements View.OnClickListener {
        private final TextView tv_result;
        public MyOnClickListener(TextView tv_result) {
            this.tv_result = tv_result;
        }
        @Override
        public void onClick (View v) {
            String desc = String.format("按钮 %s 被点击了", ((Button) v).getText());
            tv_result.setText(desc);
        }
    }
}
```

这样，为一个按钮添加事件，需要新增一个静态类，实现传入需要改变的组件，并设置组件修改方式。

还有一种方法，是让当前的Activity继承`View.OnClickListener`，然后实现`onClick`方法，在当前activity就能通过`setOnClickListener(this)`来调用当前重写的onClick方法。

而长按按钮在按钮中与普通按钮一致。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="下面的按钮英文默认大写"
        android:textColor="@color/black"
        android:textSize="17sp"/>

    <Button
        android:id="@+id/btn_single"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="单独的点击监听器"
        android:textColor="@color/black"
        android:textSize="17sp"/>

    <TextView
        android:id="@+id/tv_result"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="5dp"
        android:gravity="center"
        android:textColor="#000000"
        android:textSize="15sp"
        android:text="按钮点击结果"/>

    <Button
        android:id="@+id/btn_long"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="单独的长按监听器"
        android:textColor="@color/black"
        android:textSize="17sp"/>

</LinearLayout>
```

下面就使用Lambda来实现长按的功能。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        Button btn = findViewById(R.id.btn_single);
        TextView tv_result = findViewById(R.id.tv_result);
        btn.setOnClickListener(new MyOnClickListener(tv_result));

        Button btn_long_click = findViewById(R.id.btn_long);
        // 使用Lambda表达式设置长按监听器
        btn_long_click.setOnLongClickListener(v -> {
            // 其中的v表示调用该方法的对象，这里指btn_long_click
            String desc = String.format("按钮 %s 被长按了", ((Button) v).getText());
            tv_result.setText(desc);
            return true;
        });

    }

    static class MyOnClickListener implements View.OnClickListener {
        private final TextView tv_result;
        public MyOnClickListener(TextView tv_result) {
            this.tv_result = tv_result;
        }
        @Override
        public void onClick (View v) {
            String desc = String.format("按钮 %s 被点击了", ((Button) v).getText());
            tv_result.setText(desc);
        }
    }

}
```

此外，按钮有禁用和恢复的状态。按钮存在`enabled`属性，true为可点击，false为不可点击。

![image-20260314230910885](./README_Picture/image-20260314230910885.png)

上面长按按钮设置为false，页面中的按钮就不可点击。

### 2.1.10 图像视图

图像的图片通常位于`res/drawable`中。

![image-20260314232416532](./README_Picture/image-20260314232416532.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <ImageView
        android:id="@+id/iv_image"
        android:layout_width="match_parent"
        android:layout_height="220dp"
        android:layout_marginTop="5dp"
        android:contentDescription="一只猫"
        android:src="@drawable/cat"/>

</LinearLayout>
```

在这里通过ImageView设置了一只猫的图片，需要指定它的src和contentDescription。

```java
package com.example.chapter03;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);

        ImageView imageView = findViewById(R.id.iv_image);
        // 设置图片的src
        imageView.setImageResource(R.drawable.cat);
    }
}
```

> 这里也可以通过`setImageResource`来设置图片标签的路径。

这里创建了新的Activity后，需要在AndroidManifest上修改activity的exported为true。

![image-20260314233304771](./README_Picture/image-20260314233304771.png)

### 2.1.11 图像按钮

ImageButton是显示图片的图像按钮，继承自ImageView。

| ImageButton                  | Button                     |
| ---------------------------- | -------------------------- |
| 只能显示图片                 | 即可显示图片，也能显示文本 |
| 图片可以按比例缩放           | 图像会被拉伸变形           |
| 可以在前景和背景各放一张图片 | 只能设置背景一张图片       |

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/iv_image"
        android:layout_width="match_parent"
        android:layout_height="220dp"
        android:layout_marginTop="5dp"
        android:contentDescription="一只猫"
        android:src="@drawable/cat"/>

    <ImageButton
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:src="@drawable/cat"
        android:contentDescription="一只猫"
        android:scaleType="fitCenter"/>
</LinearLayout>
```

这里就添加了一个图像按钮。

![image-20260315225419122](README_Picture/image-20260315225419122.png)

### 2.1.12 同时显示图像和文本

同时显示图像和文本，可以使用drawable。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#FFFFFF"
        android:drawableLeft="@drawable/cat"
        android:text="图标在左"
        android:drawablePadding="50dp"/>

</LinearLayout>
```

这里设置的drawableLeft是在当前按钮的左侧添加图片。同样的，可以在这个控件的上下左右添加图片。

## 2.2 计算器项目

现在需要实现一个小项目。计算器的界面分为两大部分，第一部分是上方的计算表达式，即包含用户的输入，也包含计算结果。下方是按键。

### 2.2.1 创建Activity

首先需要创建`CalculatorActivity`。

```java
package com.example.chapter04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
}
```

### 2.2.2 创建xml页面

接下来需要创建计算器的页面，在`activyti_calculator.xml`中实现。

首先，计算器是从上到下设计布局的，因此大视图采用线性布局。如果按键过多，可能需要进行滚动，但左右不需要滚动，因此采用滚动视图`ScrollView`，页面的宽度继承父类`match_parent`，高度匹配内容`wrap_content`。

为了能够在页面中使用字符串表示按键，需要在`strings.xml`中定义字符串。

```xml
<resources>
    <string name="app_name">chapter04</string>
    <string name="calculator">简单计算器</string>
    <string name="cancel">CE</string>
    <string name="divide">÷</string>
    <string name="multiply">×</string>
    <string name="clear">C</string>
    <string name="seven">7</string>
    <string name="eight">8</string>
    <string name="nine">9</string>
    <string name="plus">+</string>
    <string name="four">4</string>
    <string name="five">5</string>
    <string name="six">6</string>
    <string name="minus">-</string>
    <string name="one">1</string>
    <string name="two">2</string>
    <string name="three">3</string>
    <string name="reciprocal">1/x</string>
    <string name="zero">0</string>
    <string name="dot">.</string>
    <string name="equal">=</string>
    <string name="sqrt">sqrt</string>
</resources>
```

还有在values下创建`dimens.xml`写计算器的文本大小和按钮高度，宽度的话可以使用0dp和权重来自适应。

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="button_font_size">30sp</dimen>
    <dimen name="button_height">75dp</dimen>
</resources>
```

首先设计最上方的计算器名称。

![image-20260315231444815](README_Picture/image-20260315231444815.png)

然后实现计算器计算结果的框。

![image-20260315231727809](README_Picture/image-20260315231727809.png)

接下来是实现按钮。

![image-20260315232340522](README_Picture/image-20260315232340522.png)

这样，就实现了第一个按钮。接下来继续实现其他按钮即可。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#EEEEEE"
    android:orientation="vertical"
    android:padding="5dp">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@string/calculator"
                android:gravity="center"
                android:textColor="black"
                android:textSize="20sp"/>

            <TextView
                android:id="@+id/tv_result"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:lines="3"
                android:background="@color/white"
                android:text="@string/zero"
                android:textColor="@color/black"
                android:textSize="25sp"
                android:gravity="end|bottom"/>

            <GridLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:columnCount="4"
                android:rowCount="5">
                <Button
                    android:id="@+id/btn_cancel"
                    android:text="@string/cancel"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_divide"
                    android:text="@string/divide"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_multiply"
                    android:text="@string/multiply"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_clear"
                    android:text="@string/clear"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_seven"
                    android:text="@string/seven"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_eight"
                    android:text="@string/eight"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_nine"
                    android:text="@string/nine"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_plus"
                    android:text="@string/plus"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_four"
                    android:text="@string/four"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_five"
                    android:text="@string/five"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_six"
                    android:text="@string/six"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_minus"
                    android:text="@string/minus"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_one"
                    android:text="@string/one"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_two"
                    android:text="@string/two"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_three"
                    android:text="@string/three"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <ImageButton
                    android:id="@+id/btn_sqrt"
                    android:contentDescription="@string/sqrt"
                    android:src="@drawable/sqrt"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:scaleType="fitCenter"
                    android:adjustViewBounds="true"
                    android:padding="8dp"
                    android:background="@null"/>
                <Button
                    android:id="@+id/btn_reciprocal"
                    android:text="@string/reciprocal"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_zero"
                    android:text="@string/zero"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_dot"
                    android:text="@string/dot"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>
                <Button
                    android:id="@+id/btn_equal"
                    android:text="@string/equal"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size"/>


            </GridLayout>
        </LinearLayout>
    </ScrollView>

</LinearLayout>
```

![image-20260315233801938](README_Picture/image-20260315233801938.png)

### 2.2.3 逻辑处理

**这里存在很多不合理的地方，暂时丢弃。**

## 2.3 Activity

Activity是屏幕组件，也就是活动。打开App时看到的每一个界面，都是一个Activity。

Activity本身不绘制组件元素，而是作为容器加载XML布局文件来展示页面信息。一个典型的应用应该包含多个Activity，如登录、主页、详情页、设置页等。

**任何Activity都需要在`AndroidManifest.xml`中声明。如果不声明，系统会无法识别并抛出异常。**

![image-20260317190808709](README_Picture/image-20260317190808709.png)

### 2.3.1 启动和停止

启动Activity也就是跳转到新页面。

```java
startActivity(new Intent(原页面.this, 目标页面.class));
```

从当前页面回到上一个页面，相当于关闭当前页面。

```java
finish(); // 结束当前的活动页面
```

现在创建两个Activity，分别是ActFinishActivity和ActStartActivity。

```java
package com.example.chapter05;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_finish);

    }
}
```

```go
package com.example.chapter05;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_start);

    }
}
```

接下来，在`activity_act_start.xml`中添加按钮，能够跳转到新页面。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ActStartActivity"
    android:orientation="vertical"
    android:gravity="center">

    <Button
        android:id="@+id/btn_act_next"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="跳转到下一个页面"/>

</LinearLayout>
```

在`ActStartActivity`中也定义按钮的功能。

```java
package com.example.chapter05;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_start);
        // 点击跳转
        findViewById(R.id.btn_act_next).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 开始新的界面
        startActivity(new Intent(this, ActFinishActivity.class));
    }
}
```

接下来，需要实现`activity_act_finish.xml`。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ActFinishActivity"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/iv_back"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:padding="5dp"
        android:layout_marginTop="20dp"
        android:src="@drawable/ic_black"/>

    <Button
        android:id="@+id/btn_finish"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="完成"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="按返回键返回"/>

</LinearLayout>
```

然后在`ActFinishActivity`中实现按钮点击返回的功能。

```java
package com.example.chapter05;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActFinishActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_finish);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // 点击这两个按钮，实现关闭页面
        if (v.getId() == R.id.iv_back || v.getId() == R.id.btn_finish) {
            finish();
        }
    }
}
```

这样，就可以实现点击图片和点击按钮返回到上一页。

![image-20260317205200612](README_Picture/image-20260317205200612.png)

### 2.3.2 生命周期

![image-20260317205406751](README_Picture/image-20260317205406751.png)

Activity的生命周期存在多个阶段。

```java
package com.example.chapter05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {
    
    private static final String TAG = "ning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ActStartActivity onCreate");
        setContentView(R.layout.activity_act_start);
        // 点击跳转
        findViewById(R.id.btn_act_next).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 开始新的界面
        startActivity(new Intent(this, ActFinishActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ActStartActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ActStartActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ActStartActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ActStartActivity onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "ActStartActivity onRestart");
    }
}
```

通过这里，就能清晰地了解到生命周期位于什么时期。

![image-20260317212134867](README_Picture/image-20260317212134867.png)

初次打开时，会执行onCreate。然后进入页面，会有onStart和onResume。onResume用于保持当前页面在线。

如果离开页面，会执行onPause，回到页面执行onRestart。

### 2.3.3 启动模式

Android对于多个Activity启动，有很多种处理方式。最经典的就是使用任务栈，将第一个Activity入栈，接着开启的其他Activity就依次入栈，关闭Activity时也是依次出栈。

Android有四种启动模式。

#### 2.3.3.1 Standard 标准模式

每次启动Activity，系统都会创建Activity实例并压入任务栈中，按顺序入栈和出栈。这是默认的启动方式。

设置方法在`AndroidManifest.xml`中。

```xml
<activity
    android:name=".ActStartActivity"
    android:exported="true"
    android:launchMode="standard">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

#### 2.3.3.2 singleTop 栈顶复用模式

如果如果栈顶的Activity就是接下来要新建的Activity，就不会重复创建新的Activity入栈，而是直接复用。

```xml
<activity
    android:name=".ActStartActivity"
    android:exported="true"
    android:launchMode="singleTop">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

这种适用于微信应用打开微信支付、打开微信小程序等，就可以使用栈顶复用，避免新建多个Activity入栈。

#### 2.3.3.3 SingleTask栈内复用

如果新建的Activity存在于栈中，那么就会将该栈帧上的所有栈元素全部出栈，然后将非目标栈帧全部入栈，让当前的栈元素位于栈顶。

```xml
<activity
    android:name=".ActStartActivity"
    android:exported="true"
    android:launchMode="singleTask">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

#### 2.3.3.4 SingleInstance单实例模式

Activity独占一个栈，这个栈只能有一个实例。后续任何请求都会复用这一个实例。

```xml
<activity
    android:name=".ActStartActivity"
    android:exported="true"
    android:launchMode="singleInstance">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

