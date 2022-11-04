<!DOCTYPE html>
<html>
<head>
    <title>BookShop</title>
<#--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">-->
</head>
<body>
<#--<header class="p-3 text-bg-dark">-->
<#--    <div class="container">-->
<#--        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">-->
<#--            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">-->
<#--                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>-->
<#--            </a>-->

<#--            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">-->
<#--                <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>-->
<#--                <li><a href="#" class="nav-link px-2 text-white">Features</a></li>-->
<#--                <li><a href="#" class="nav-link px-2 text-white">Pricing</a></li>-->
<#--                <li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>-->
<#--                <li><a href="#" class="nav-link px-2 text-white">About</a></li>-->
<#--            </ul>-->

<#--            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search" action="/" method="get">-->

<#--                <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">-->
<#--            </form>-->
<#--            <#list products as product>-->
<#--                <div>-->
<#--                    <p><b>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее...</a></p>-->
<#--                </div>-->
<#--            <#else>-->
<#--                <h3>Товаров нет</h3>-->
<#--            </#list>-->
<#--            <hr>-->
<#--            <div class="text-end">-->
<#--                <button type="button" class="btn btn-outline-light me-2">Login</button>-->
<#--                <button type="button" class="btn btn-warning">Sign-up</button>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</header>-->
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Название объявления: <input type="text" name="title"/><br><br>
    Описание объявления: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>