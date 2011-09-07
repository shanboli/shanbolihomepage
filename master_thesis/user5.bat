del *.bbl
copy master_thesis.bib master_thesis_publish.bib /y
latex master_thesis_publish
bibtex master_thesis_publish
latex master_thesis_publish
pdflatex master_thesis_publish
pdflatex master_thesis_publish
start master_thesis_publish.pdf
