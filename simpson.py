@base <https://simpsons.fandom.com/pt/wiki/P%C3%A1gina_principal/> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix sim: <http://www.ifi.uio.no/IN3060/simpsons#> .
@prefix fam: <http://www.ifi.uio.no/IN3060/family#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix rel: <http://www.perceive.net/schemas/relationship/> .

from rdflib import Graph
g = Graph()
g.parse('http://dbpedia.org/resource/Semantic_Web')

for s, p, o in g:
    print(s, p, o)