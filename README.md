# Consulta CEP - ViaCEP API

## 🧭 Visão Geral

Este projeto em Java realiza a **consulta de endereços a partir de um CEP** utilizando a **API pública ViaCEP**. Ele consome os dados via HTTP, converte o retorno JSON para um objeto Java usando a biblioteca **Gson** e salva o resultado em um arquivo `.json` local.

## 🚀 Funcionalidades

* Validação do formato de CEP digitado (somente 8 números)
* Requisição HTTP à API ViaCEP
* Conversão do JSON retornado para um objeto `Cep`
* Tratamento de erros de rede, CEP inexistente e formato inválido
* Salvamento dos dados em um arquivo JSON formatado

## 🧩 Estrutura do Projeto

```
📂 projeto-consulta-cep
 ┣ 📂 main
 ┃ ┗ 📜 Main.java
 ┣ 📂 models
 ┃ ┗ 📜 Cep.java
 ┣ 📜 README.md
 ┗ 📜 cep_exemplo.json (gerado após a execução)
```

## 🧠 Classe Principal (`Main.java`)

A classe principal realiza:

1. Leitura do CEP via `Scanner`.
2. Validação de formato (8 dígitos).
3. Requisição HTTP via `HttpClient`.
4. Conversão de JSON para objeto `Cep` com Gson.
5. Salvamento dos dados em arquivo local.

## 🧱 Classe de Modelo (`models.Cep`)

```java
package models;

public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String ddd;
    private String estado;

    public Cep(String cep, String logradouro, String bairro, String ddd, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.ddd = ddd;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cep{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", ddd='" + ddd + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
```

## 🧰 Tecnologias Utilizadas

* **Java 17+**
* **Gson (Google JSON Library)**
* **HTTPClient (java.net.http)**
* **ViaCEP API** ([https://viacep.com.br/](https://viacep.com.br/))

## 💾 Exemplo de Execução

```
Digite o CEP para consulta (somente os 8 números):
01001000

📄 Resultado da consulta:
Cep{cep='01001-000', logradouro='Praça da Sé', bairro='Sé', ddd='11', estado='SP'}

✅ Dados salvos com sucesso em: 01001000.json
```

## ⚠️ Tratamento de Erros

* CEP inválido (menos de 8 dígitos ou caracteres não numéricos)
* Falha na conexão com a API
* CEP inexistente (retorna `"erro": true`)
* Problemas de escrita no arquivo local

## 🪄 Melhorias Futuras

* Criar um menu interativo para múltiplas consultas
* Permitir salvar o histórico de consultas
* Implementar interface gráfica (JavaFX ou Swing)

## 🧑‍💻 Autor

**Willian Diniz** 
Linkedin: https://www.linkedin.com/in/willian-diniz-2360b74b/
GitHub: https://github.com/SBEWill
 
 Projeto desenvolvido durante os estudos de **consumo de API, manipulação de JSON e persistência de dados** no curso *Java: consumindo API, gravando arquivos e lidando com erros* do programa **One/Alura**.
