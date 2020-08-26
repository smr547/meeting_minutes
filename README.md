# meeting_minutes
Create meeting agendas and minutes based on ``issues`` in a GitHub repository

## Background
We (4 collaborators) run regular zoom meetings to discuss issues and plan activities related to our current project. We want to generate minutes 
of the meeting to capture results of the discussion. 

## This project
Create a Python program that will generate Meeting Minutes as a PDF for distribution to all collaborators.

## Usage

```
./generate_minutes.py -p my_project_url -d 25/10/2020 --actionLabel Action --output myProject_2020_10_25_minutes.pdf
```

The program will search all issues with ``Action`` label and list the comments dated ``25/10/2020``. The output will be
written to a PDF ``myProject_2020_10_25_minutes.pdf``

## Routine agendas

Here is an idea for creating agendas

* enter each agenda topic as a GitHub issue, label it as  ``Agenda Item``
* prepend ``00``, ``01``, ``02`` to the title of each issue to allow the desired ordering of the agenda items
* immediately close each issue so as not to pollute your list of "real" issues
* produce your agenda PDF using ``./generate_agenda.py``

## Resources to use
The project will be built using:

* Python3 programming languague
* [PyGitub](https://github.com/PyGithub/PyGithub) to access issues in the GitHub repository
* [pyFPDF](https://pypi.org/project/fpdf/) to create the PDF file

## Project status
Just an idea at this stage
