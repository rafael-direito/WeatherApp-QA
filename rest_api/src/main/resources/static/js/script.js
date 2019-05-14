function getImagePath(element, weather) 
{
  console.log(element.name);
  var my_src = "";

  switch (weather) {
    case ("Clear sky"):
      my_src = "https://image.flaticon.com/icons/svg/169/169367.svg";
      break;
    case ("Partly cloudy"):
      my_src = "https://image.flaticon.com/icons/svg/861/861059.svg";
      break;
    case ("Sunny intervals"):
      my_src = "https://image.flaticon.com/icons/svg/861/861059.svg";
      break;
    case ("Cloudy "):
      my_src = "https://image.flaticon.com/icons/svg/1163/1163624.svg";
      break;
    case ("Cloudy (High cloud)"):
      my_src = "https://image.flaticon.com/icons/svg/1163/1163624.svg";
      break;
    case ("Showers"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Light showers"):
      my_src = "https://image.flaticon.com/icons/svg/131/131041.svg";
      break;
    case ("Heavy showers"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Rain"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Light rain"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Heavy rain"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Intermittent rain"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Intermittent ligth rain"):
      my_src = "https://image.flaticon.com/icons/svg/131/131041.svg";
      break;
    case ("Intermittent heavy rain"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146858.svg";
      break;
    case ("Drizzle"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146870.svg";
      break;
    case ("Mist"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146870.svg";
      break;
    case ("Snow"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146899.svg";
      break;
    case ("Thunderstorms"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146860.svg";
      break;
    case ("Showers and thunderstorms"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146860.svg";
      break;
    case ("Hail"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146862.svg";
      break;
    case ("Frost"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146899.svg";
      break;
    case ("Rain and thunderstorms"):
      my_src = "https://image.flaticon.com/icons/svg/1146/1146860.svg";
      break;
    case ("Convective clouds"):
      my_src = "https://image.flaticon.com/icons/svg/1164/1164981.svg";
      break;
    case ("Partly cloudy"):
      my_src = "https://image.flaticon.com/icons/svg/1163/1163624.svg";
      break;
    case ("Fog"):
      my_src = "https://image.flaticon.com/icons/svg/1164/1164981.svg";
      break;
    case ("Cloudy"):
      my_src = "https://image.flaticon.com/icons/svg/1163/1163624.svg";
      break;
    default:
      my_src = "https://cdn.oculosshop.com.br/media/catalog/product/placeholder/default/placeholder-image_1.png";
  }

  element.src=my_src;
}