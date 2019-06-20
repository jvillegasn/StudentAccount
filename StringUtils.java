

public class StringUtils
{
	// method that formats the display of a string
	public static String padSpaces(String s, int length, String t) // string, length of string, fill spaces with string
	{
		if (s.length() < length)
		{
			StringBuilder sb = new StringBuilder(s);
			while(sb.length() < length)
			{
				sb.append(t);
			}
			return sb.toString();
		}
		else
		{
			return s.substring(0, length);
		}
	}
}