# Points of Interest

Desafio [Pontos de Interesse por GPS](https://github.com/backend-br/desafios/blob/master/points-of-interest/PROBLEM.md) por [Back-End Brasil](https://github.com/backend-br).

## Descrição do Problema

Este é um desafio de programação que envolve a manipulação de pontos de interesse (POIs) em um plano cartesiano. O objetivo é implementar um sistema que permita calcular os POIs mais próximos de uma determinada coordenada.

O problema consiste em três partes principais:

1. **Cálculo de Proximidade:** Dada uma coordenada, o sistema deve determinar os POIs mais próximos a essa coordenada.
2. **Saída de Resultados:** Os POIs mais próximos a partir de um raio máximo devem ser exibidos de alguma forma, seja em formato de lista, mapa ou outro meio.

## Estrutura do Repositório

O repositório contém os seguintes arquivos e pastas:

- `src/`: Pasta contendo o código-fonte em Java da solução.
- `test/`: Pasta contendo testes unitários para validar a solução.

## Endpoints da API

### Cadastrar POI
Adiciona um novo ponto de interesse (POI) ao sistema.

Parâmetros
| Nome       | Descrição                              |
|------------|----------------------------------------|
| `name`     | O nome do ponto de interesse.         |
| `x`        | A coordenada x do ponto de interesse. |
| `y`        | A coordenada y do ponto de interesse. |e.

#### Exemplo de Requisição

##### [POST] localhost:8080/points

```bash
{
    "name": "Escola",
    "x": 2,
    "y": 4
}
```
  
#### Resposta de Sucesso
```bash
{
    "id": 1,
    "name": "Escola",
    "x": 2,
    "y": 4
}
```

### Listar POIs
Lista todos os pontos de interesse (POIs) presentes no sistema.

#### Exemplo de Requisição

##### [GET] localhost:8080/points
  
#### Resposta de Sucesso
```bash
[
	{
		"id": 1,
		"name": "escola",
		"x": 2,
		"y": 4
	},
	{
		"id": 1,
		"name": "parque",
		"x": 2,
		"y": 3
	},
	{
		"id": 1,
		"name": "igreja",
		"x": 12,
		"y": 11
	}
]
```

### Listar POIs por proximidade
Calcula os pontos de interesse (POIs) mais próximos a partir de um raio máximo.

Parâmetros
| Nome       | Descrição                              |
|------------|----------------------------------------|
| `x`        | A coordenada x do ponto de interesse. |
| `y`        | A coordenada y do ponto de interesse. |
| `radius`   | O raio máximo até o POI.              |e.

### Exemplo de Requisição

#### [GET] localhost:8080/points/near?x=5&y=5&dmax=10
  
### Resposta de Sucesso
```bash
[
	{
		"id": 1,
		"name": "escola",
		"x": 2,
		"y": 4
	},
	{
		"id": 1,
		"name": "parque",
		"x": 2,
		"y": 3
	}
]
```

## Executando a Solução

Para executar a solução em Java, siga estas etapas:

1. Certifique-se de ter o JDK (Java Development Kit) instalado em sua máquina.
2. Clone este repositório em sua máquina local.
3. Execute as tasks do gradle.

