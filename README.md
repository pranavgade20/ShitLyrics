# ShitLyrics

Generating(very bad) lyrics using a markov chain model written from scratch in Java. Thanks to https://github.com/SwagLyrics for the training data.

## Using

Most of the high level magic happens in `src/Main.java`. Run `Main.main()` to generate lyrics. Unfortunately, you need to modify source code to tinker around stuff. For example, change the constant `WORDS_LIMIT` in `src/Main.java` to set the number of words to generate.

## Results

`can i think i know i know you?" 
 ain't got a hell 
 new religion 
 when he make that we'd be so once again 
 i'm sad bitch i was the jones right out there is too long and you understand me? 
 i feel worse yeah 
 create without conflict just can't change 
 no masters here 
 burn the glam 
 boy i can feel fine now and i don't leave me" 
 you fall apart 
 through 
 no game has fallen for the flu 
 fuck i could take me make me and goodnight 
 these heads with a new chick i know we wanted to see a request mi have got a mutant 
 feel the atlantic 
 i'm all night was in 
 fuck but i tell by your cup girl i don't ball then double it ain't the time 
 even look like badass 
 can be complete 
 not your mind but i choose to everybody fucks 
 but that lost it can see it sucks 
 maybe we really wanna call my friends and seek 
 would 
 with him tonight 
 stay with me rich i go stupid obnoxious or how long 
 pressure pushing for next 
 your memory will you get you you to blame so wide 
 oh `

## How

If you are wondering how I generated these *awesome* lyrics, here is what I did:

- Use `Cleaner.java` to clean the data(lyrics.txt) to remove unnecessary characters and non English words from the  training data, to give cleaned.txt.
- Train the model (very inefficiently, please don't use this implementation for something serious) with the cleaned data.
- Choose a random word as the starting and keep feeding the words we get back to the model to keep generating lyrics for as long as we want to.
- P.S.: words.txt is just https://github.com/dwyl/english-words/blob/master/words.txt, used for filtering non english words from the training data.