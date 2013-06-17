# ANTLR-Workshop

Example source code for the ANTLR workshop. 

## NLP

Workshop exercise. For the not implemented version see the tag 'workshop'.

This project use for the generation of UML Scruffy a offline YUML like diagram generator. See the section install Scruffy for more information.

Example input:
```plain
De HAN heeft minimaal 100 studenten.
Een student heeft maximaal 1 begeleider.
De HAN heeft maximaal 20 begeleiders.
Een student heeft een vak.
```

## TypeText

Example with a listener and visitor that shows how to extract de text of book items.

Format to parse:
```plain
id:type { "Text" };
```

Example input:
```plain
antlr:Book { "The Definitive ANTLR 4 Reference" };
jamesBondGoldfinger:Movie { "James Bond Goldfinger." };
biographySteveJobs:Book { "Biography Steve Jobs" };
djangoUnchained:Movie { "Django Unchained." };
```

## Scruffy 

### Linux

Install Scruffy on Debian or Ubuntu.

1. Install Python 2.7 ```sudo apt-get install python2.7```
2. Install graphviz ```sudo apt-get install graphviz```
3. Install librsvg2-bin ```sudo apt-get install librsvg2-bin```
4. Install plotutils ```sudo apt-get install plotutils```
5. Install PIL (Image module) ```sudo apt-get install python2.7-imaging```
6. Download Scruffy from https://github.com/aivarsk/scruffy.
7. Install Scruffy ```sudo ./setup.py install```

## Authors

**Arjan Oortgiese**

+ [https://github.com/ArjanO](https://github.com/ArjanO)

**Boyd Hofman**

+ [https://github.com/BoydHofman](https://github.com/BoydHofman)

**Joëll Portier**

+ [https://github.com/Sjoel](https://github.com/Sjoel)

**Michiel Westerbeek**

+ [https://github.com/happylinks](https://github.com/happylinks)

**Tim Waalewijn**

+ [https://github.com/timwaalewijn](https://github.com/timwaalewijn)

## MIT License
Copyright (c) 2013 HAN University of Applied Sciences
Arjan Oortgiese
Boyd Hofman
Joëll Portier
Michiel Westerbeek
Tim Waalewijn

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
