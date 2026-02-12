dim result

result = msgbox("Will you be my Valentine?", 4 , "Happy Valentine's Day")
count=0

Do While result<>6

If count=5 then
judge=msgbox("Fine, Cooper will go with me", 0 ,"Woe, pain even")
Exit Do
else
result=msgbox("Please?",4,"Negotiations")
end if
count=count+1

Loop

If result=6 then
yay=msgbox("Yay",0,"Success")
else
fail=msgbox("I cri",0,"Utter Failure")
end if