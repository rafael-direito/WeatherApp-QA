function getImagePath(element, weather) 
{
  console.log(element.name);
  var my_src = "";

  switch (weather) {
    case ("Clear sky"):
      my_src = "https://cdn-icons-png.flaticon.com/512/869/869869.png";
      break;
    case ("Partly cloudy"):
      my_src = "https://cdn-icons-png.flaticon.com/512/3208/3208752.png";
      break;
    case ("Sunny intervals"):
      my_src = "https://cdn-icons-png.flaticon.com/512/1146/1146869.png";
      break;
    case ("Cloudy "):
      my_src = "https://cdn-icons-png.flaticon.com/512/414/414927.png";
      break;
    case ("Cloudy (High cloud)"):
      my_src = "https://cdn-icons-png.flaticon.com/512/414/414927.png";
      break;
    case ("Showers"):
      my_src = "https://cdn-icons-png.flaticon.com/512/3075/3075858.png";
      break;
    case ("Light showers"):
      my_src = "https://cdn-icons-png.flaticon.com/512/3075/3075858.png";
      break;
    case ("Heavy showers"):
      my_src = "https://cdn-icons-png.flaticon.com/128/3767/3767039.png";
      break;
    case ("Rain"):
      my_src = "https://cdn-icons.flaticon.com/png/512/4088/premium/4088981.png?token=exp=1652008189~hmac=30d1e3b38c3bda5959c8e00d873fd6fe";
      break;
    case ("Light rain"):
      my_src = "https://cdn-icons-png.flaticon.com/128/3767/3767039.png";
      break;
    case ("Heavy rain"):
      my_src = "https://cdn-icons.flaticon.com/png/512/4088/premium/4088981.png?token=exp=1652008189~hmac=30d1e3b38c3bda5959c8e00d873fd6fe";
      break;
    case ("Intermittent rain"):
      my_src = "https://cdn-icons-png.flaticon.com/128/3767/3767039.png";
      break;
    case ("Intermittent ligth rain"):
      my_src = "https://cdn-icons-png.flaticon.com/128/3767/3767039.png";
      break;
    case ("Intermittent heavy rain"):
      my_src = "https://cdn-icons-png.flaticon.com/512/1146/1146858.png";
      break;
    case ("Drizzle"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Mist"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Snow"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Thunderstorms"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Showers and thunderstorms"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Hail"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Frost"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Rain and thunderstorms"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Convective clouds"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Partly cloudy"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Fog"):
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
      break;
    case ("Cloudy"):
      my_src = "https://cdn-icons-png.flaticon.com/512/414/414927.png";
      break;
    default:
      my_src = "https://cdn-icons-png.flaticon.com/512/189/189665.png";
  }

  element.src=my_src;
}