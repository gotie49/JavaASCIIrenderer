# ASCIIshader


Links:

https://medium.com/@shubham0473/unleash-your-inner-artist-a-step-by-step-guide-to-converting-images-to-ascii-art-using-java-97860464f19a
https://www.youtube.com/watch?v=gg40RWiaHRY

- Console Application (CLI)
- Reads .png or .jpeg Image Files (max size to be defined e.g. 500x500px)
- Rendered Image results in a 500x500 character ASCII String
- returns ASCII String in the console

-Image Data Reader 
  -get pixel positions and saturation/luminance information of the image
  -check for type
-Downscale Image by a Factor of 8
  -Divide Resulution by 8 to achieve that
-Desaturate Image
-Quantize the Luminance for every Pixel to 0...0.1...1
  -Math.Floor(L*10)/10
- Assign the Luminance of the Pixel to a Character out of the ASCII MAP: .;coPBO?@■
  




