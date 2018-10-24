package polynomial;

import java.math.BigInteger;
import java.util.Iterator;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
            if(t.getCoefficient().equals(BigInteger.ZERO))
            {
                
                return ;
            }
            
		if(polynomial.isEmpty())
                {
                    
                    polynomial.addFirst(t);
                    
                    return;
                         
                }
                
                
                
               int index = 0;
                 Boolean isadded = false;
		for (Term currentTerm: polynomial)
		{
                    if(currentTerm.getExponent() < t.getExponent())
                    {
                        isadded = true;
                        if(index == 0)
                        {
                            polynomial.addFirst(t);
                            break;
                        }
                        else
                        {
                            
                            polynomial.add(index-1, t);
                            break;
                        }
                        
                        
                    }
                    if(currentTerm.getExponent() == t.getExponent())
                    {
                        
                        isadded = true;
                        currentTerm.setCoefficient(t.getCoefficient());
                        break;
                    }
                    
                       index++;
		}
                if(!isadded)
                {
                     polynomial.addLast(t);
                    
                } 
               
		
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
            
            Polynomial result = new Polynomial();
            int p1index = 0;
            int index = 0;
            
           
          
                    
           
                
                
                
            int p11index = 0;
            int p22index = 0;
            
            for(int i = 0 ; i < Math.max(p1.polynomial.size(),p2.polynomial.size() ) ; i++)
            {
                Term newcurrent = new Term(1,BigInteger.ONE);
                if( p22index >= p2.polynomial.size() || p11index >= p1.polynomial.size())
                {
                    
                    break;
                    
                }
                Term p11 = p1.getTerm(p11index);
                Term p22 = p2.getTerm(p22index);
                
                if(p11.getExponent() > p22.getExponent())
                {
                    newcurrent.setExponent(p11.getExponent());
                    newcurrent.setCoefficient(p11.getCoefficient());
                    
                   result.addTerm(newcurrent);
                    p11index++;
                    
                }
                else
                    if(p11.getExponent() < p22.getExponent())
                {
                    
                    newcurrent.setExponent(p22.getExponent());
                    newcurrent.setCoefficient(p22.getCoefficient());
                    result.addTerm(newcurrent);
                    p22index++;
                }
               else
                {
                     if(p22index < p2.polynomial.size() && p11index < p1.polynomial.size())
                     {
                         
                         newcurrent.setExponent(p22.getExponent());
                     newcurrent.setCoefficient(p22.getCoefficient().add(p11.getCoefficient()));
                     result.addTerm(newcurrent);
                     p11index++;
                     p22index++;
                         
                     }
                     
                        
                }
                
                
            }
                
                
            for(int k = p11index ; k < p1.polynomial.size() ; k++)
            {
                
                
                result.addTerm(p1.getTerm(k));
                
            }
            
                 
            for(int k = p22index ; k < p2.polynomial.size() ; k++)
            {
                
                
                result.addTerm(p2.getTerm(k));
                
            }
            
		
		return result;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
            
            for(Term current:polynomial)
            {
                
                current.setCoefficient(current.getCoefficient().multiply(t.getCoefficient()));
                current.setExponent(current.getExponent()+t.getExponent());
                
                
                
            }
		
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		
            Polynomial result = new Polynomial();
            
            for(Term current:p1.polynomial)
            {
                
                for(Term current2 : p2.polynomial)
                {
                    
                    Boolean toadd = false;
                    Term resultant = new Term(1, BigInteger.ONE);
                    resultant.setCoefficient(current.getCoefficient().multiply(current2.getCoefficient()));
                    resultant.setExponent(current.getExponent()+current2.getExponent());
                    for(Term prev : result.polynomial)
                    {
                        if(prev.getExponent() == resultant.getExponent())
                        {
                            toadd = true;
                           
                            prev.setCoefficient(prev.getCoefficient().add(resultant.getCoefficient()));
                            
                            break;
                        }
                        
                    }
                    if(!toadd)
                    {
                        
                        result.addTerm(resultant);
                        
                    }
                     
                    
                    
                }
               
                
                
                
            }
		return result;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/
            Polynomial poly = new Polynomial();
            poly = this.deepClone();
            
            int size = poly.polynomial.get(0).getExponent();
            int i = 0;
            while(i < size)
            {
                
                System.out.println(size);
                if(!(poly.polynomial.get(i).getExponent() == (size-i)))
                {
                    
                    poly.addTerm(new Term((size-i), new BigInteger("0")));
                    
                }
                
                    
                 i++;
                    
                
                
                
                
            }
            
            BigInteger result = new BigInteger("1");
            Iterator iterator = poly.polynomial.iterator();
            Term head = (Term)iterator.next();
            //System.out.println(head.getCoefficient());
            result = head.getCoefficient();
            int prevexp = head.getExponent();
            Term term = null;
            while(iterator.hasNext())
            {
                
                Term val = (Term)iterator.next();
                result = result.multiply(x).add(val.getCoefficient());
                
//               if(term == null)
//               {
//                   
//                   term = (Term)iterator.next();
//               }
//               
//                   
//                   
//               
//               
//               
//                if(prevexp-term.getExponent() != 1)
//                {
//                    result = x.multiply(result);
//                    prevexp--;
//                    
//                }
//                else
//                {
//                    
//                    result = x.multiply(result).add(term.getCoefficient());
//                    prevexp--;
//                    term = (Term)iterator.next();
//                }
                        
               
               
                
            }
            
            return result;

            //return new BigInteger("0");
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
