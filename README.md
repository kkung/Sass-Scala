Sass-Scala
==========

Written by MinYoung Jung (kkung)

http://github.com/kkung/sass-scala

About Sass-Scala
----------------

Sass-Scala is a wrapper around [libsass](http://github.com/hcatlin/libsass).

Sass-Scala using [JNA](https://github.com/twall/jna).

Build
-----

```bash
$ git submodule init
$ git submodule update
    
$ sbt compile
```

Usage
-----

```scala

    val result = Sass.compile("p { width: 1+1px; }")
    result match {
        case (true, compiled) => compiled
        case (false, err) => err
    }

```


License
-------

MIT License.
