function calcularPresupuesto(ingresoTotal) {
  const gastosNecesarios = ingresoTotal * 0.5;
  const gastosOpcionales = ingresoTotal * 0.3;
  const ahorroInversion = ingresoTotal * 0.2;

  console.log(`Gastos necesarios (50%): $${gastosNecesarios.toFixed(2)}`);
  console.log(`Gastos opcionales (30%): $${gastosOpcionales.toFixed(2)}`);
  console.log(`Ahorro e inversión (20%): $${ahorroInversion.toFixed(2)}`);
}

function iniciarPrograma() {
  const nombreUsuario = prompt("Ingrese su nombre:");
  const claveUsuario = prompt("Ingrese su clave(12345):");
  const claveCorrecta = "12345"; // Clave predeterminada para el ejemplo
  
  if (claveUsuario === claveCorrecta) {
    const edadUsuario = parseInt(prompt("Ingrese su edad:"), 10);
    const ingresoTotal = parseFloat(prompt("Ingrese su ingreso total mensual:"));

    if (!isNaN(edadUsuario) && !isNaN(ingresoTotal) && ingresoTotal > 0) {
      console.log(`Hola, ${nombreUsuario}. Aquí está tu presupuesto:`);
      calcularPresupuesto(ingresoTotal);
    } else {
      console.log("Por favor, ingrese valores válidos para edad e ingreso.");
    }
  } else {
    console.log("Clave incorrecta. Acceso denegado.");
  }
}
iniciarPrograma();