from os import path

from setuptools import setup, find_packages

here = path.abspath(path.dirname(__file__))

setup(
    name='hunlp',
    version='0.2.0',
    description='HuNlp Python wrapper',
    url='https://github.com/oroszgy/hunlp',
    author='Gyorgy Orosz',
    author_email='gyorgy@orosz.link',
    license='LGPL3',
    classifiers=[
        'Development Status :: 3 - Alpha',
        'Intended Audience :: Developers',
        'Programming Language :: Python :: 3.5',
    ],
    keywords='nlp hungarian ner magyarlanc pos-tagging dependency-parsing',
    packages=find_packages(exclude=['contrib', 'docs', 'tests']),
    install_requires=[],
)
