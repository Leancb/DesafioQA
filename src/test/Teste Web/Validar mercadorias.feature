# new feature
# language: pt

Funcionalidade:
  Eu sabendo que para a próxima entrega no site de produção
  Preciso  validar os campos de busca de "Marca", "Modelo" e "Versão" da página de resultado de busca da Webmotors, além da listagem de estoque de uma determinada loja.
  Para garantir atender os requisitos do teste.

  Cenário:          Validar  buscar Carros por "Marca" e "Modelo" também pesquisando "Versão" - Positivo
  Dado              configurei ambiente para acessar URL Chrome
  E                 abri tela WebMotors
  Quando            validar buscar carro por marca, pesquisando menu esquerdo modelo, versao e validando retorno das pesquisas
  E                 validar buscar carro por modelo
  Entao             validacao efetuada e fecho a tela

  Cenário:          Validar buscar Motos por "Marca" (botão pesquisar motos por marca com erro na segunda pesquisa, sempre volta resultado da pesquisa anterior)
  Dado              configurei ambiente para acessar URL Chrome
  E                 abri tela WebMotors
  Quando            validar buscar moto por marca
  Entao             validacao efetuada e fecho a tela

  Cenário:          Validar buscar Motos por "Modelo" (botão pesquisar motos por modelo com erro na segunda pesquisa, sempre volta  resultado da pesquisa anterior)
  Dado              configurei ambiente para acessar URL Chrome
  E                 abri tela WebMotors
  Quando            validar buscar moto por modelo
  Entao             validacao efetuada e fecho a tela

  Cenário:            Validar estoque da loja
    Dado              configurei ambiente para acessar URL Chrome
    E                 abri pagina da loja
    Entao             valido que retornou o estoque


