# most-used-sentences-finder
Most used sentences finder in pdf file

## How to use
I tested it in Mac with Java8
```sh
$ git clone https://github.com/SongKJ00/most-used-sentences-finder.git
$ cd most-used-sentences-finder
$ java -jar out/artifacts/my_utility_jar/my-utility.jar <path-to-pdf-file(absolute path)> <maximum number to get>
```

## Example
### Download sample pdf file
First, download sample pdf file.

I used [tensorflow_tutorial.pdf](https://www.tutorialspoint.com/tensorflow/tensorflow_tutorial.pdf)
```sh
$ wget https://www.tutorialspoint.com/tensorflow/tensorflow_tutorial.pdf
```

### Run jar file
Then, run the jar file
```
$ java -jar out/artifacts/my_utility_jar/my-utility.jar <path-to-tensorflow_tutorial.pdf> <maximum number to get>
```

Command that I used
```
$ java -jar out/artifacts/my_utility_jar/my-utility.jar $HOME/Downloads/tensorflow_tutorial.pdf 10
```

### Result
It prints output with tuple in descending order based on number of uses of the each sentences.

On each line, first one represents most used sentence, second one represensts number of uses of the sentence

```
( ,681)
(TensorFlow        ,90)
(  ,27)
(import tensorflow as tf ,12)
(import numpy as np ,10)
(import matplotlib.pyplot as plt ,7)
(with tf.Session() as sess: ,6)
(plt.show() ,5)
(from tensorflow.examples.tutorials.mnist import input_data ,4)
(plt.legend() ,4)
```
