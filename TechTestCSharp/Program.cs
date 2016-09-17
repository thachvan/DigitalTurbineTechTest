using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TechTestCSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            Car car = new Car(Car.CarType.Hybrid, Car.CarColor.Black, 4, "V6");
            car.Print();

            Console.WriteLine();
            Console.WriteLine("Setting wheel diameter and tire size...");
            Console.WriteLine();

            car.SetWheelDiameter(16);
            car.SetTireSize(185, 50);
            car.Print();

            Console.ReadLine();
        }
    }
}
