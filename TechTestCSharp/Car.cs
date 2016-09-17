using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TechTestCSharp
{
    class Car
    {
        public enum CarType { Sport, Sedan, Hybrid, Luxury, Racing };
        public enum CarColor { Red, Black, White, Gray };

        private CarType type;
        private CarColor color;
        private int doors;
        private string engine;
        private int wheelDiameter;
        private Dictionary<string, int> tireSize = new Dictionary<string, int>();

        public Car(CarType carType, CarColor carColor, int carDoors, string carEngine)
        {
            type = carType;
            color = carColor;
            doors = carDoors;
            engine = carEngine;
        }

        public CarType GetType()
        {
            return type;
        }

        public CarColor GetColor()
        {
            return color;
        }

        public int GetDoors()
        {
            return doors;
        }

        public string GetEngine()
        {
            return engine;
        }

        public int GetWheelDianeter()
        {
            return wheelDiameter;
        }

        public Dictionary<string, int> getTireSize()
        {
            return tireSize;
        }

        public bool SetWheelDiameter(int diameter)
        {
            if (diameter<15 || diameter > 19)
            {
                return false;
            }
            else
            {
                wheelDiameter = diameter;
                return true;
            }
        }

        public bool SetTireSize(int width, int height)
        {
            if (width<=0 || height<=0)
            {
                return false;
            }
            else
            {
                tireSize.Clear();
                tireSize.Add("width", width);
                tireSize.Add("height", height);
                return true;
            }
        }

        public void Print()
        {
            Console.WriteLine("{");
            Console.WriteLine("\tType: " + type + ",");
            Console.WriteLine("\tColor: " + color + ",");
            Console.WriteLine("\tDoors: " + doors + ",");
            Console.WriteLine("\tEngine: " + engine + ",");
            Console.WriteLine("\tWheel diameter: " + wheelDiameter + ",");

            int tireWidth, tireHeight;
            Console.Write("\tTire size: {");
            if (tireSize.TryGetValue("width", out tireWidth) && tireSize.TryGetValue("height", out tireHeight))
            {
                Console.Write("width: " + tireWidth + ", ");
                Console.Write("height: " + tireHeight);
            }
            Console.WriteLine("}");
            Console.WriteLine("}");
        }
    }
}
